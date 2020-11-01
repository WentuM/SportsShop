package servlets;

import dao.UserDaoImpl;
import model.User;
import mysql.PaswordHash;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private UsersService usersService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        UserDaoImpl userDao = new UserDaoImpl();
        usersService = new UsersServiceImpl(userDao);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "on".equals(rememberMeStr);

        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (email == null || password == null || email.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Заполните все поля авторизации";
        } else {
            try {
                user = usersService.findByEmail(email.trim());
                if (user == null) {
                    hasError = true;
                    errorString = "Почта или пароль были введены неверно";
                } else {
                    password = PaswordHash.hash(password.trim());
                    if (!password.equals(user.getPassword())) {
                        hasError = true;
                        errorString = "Почта или пароль были введены неверно";
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if (hasError) {

            request.setAttribute("errorString", errorString);
            request.getServletContext().getRequestDispatcher("/login.ftl").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loginedUser", user.getEmail());

            if (remember) {
                Cookie cookieUser = new Cookie("userEmail", user.getEmail());
                cookieUser.setMaxAge(60*60*24*365);
                response.addCookie(cookieUser);
            }
            response.sendRedirect("/main");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("loginedUser") == null) {
            req.getServletContext().getRequestDispatcher("/login.ftl").forward(req, resp);
        } else {
            resp.sendRedirect("/profile");
        }
    }
}
