package servlets;

import dao.UserDaoImpl;
import model.User;
import mysql.MySQLConnUtils;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
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
        boolean remember = "Y".equals(rememberMeStr);

        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (email == null || password == null || email.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Заполните все поля авторизации";
        } else {
            try {
                Connection conn = MySQLConnUtils.getMySQLConnection();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            try {
                // Найти user в DB.
                user = usersService.findByEmail(email);

                if (user == null) {
                    hasError = true;
                    errorString = "Почта или пароль были введены неверно";
                }

            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // В случае, если есть ошибка,
        // forward (перенаправить) к /WEB-INF/views/login.jsp
        if (hasError) {

            // Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);

            // Forward (перенаправить) к странице /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login");
            dispatcher.forward(request, response);
        }
        // В случае, если нет ошибки.
        // Сохранить информацию пользователя в Session.
        // И перенаправить к странице userInfo.
        else {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());

            // Если пользователь выбирает функцию "Remember me".
            if (remember) {
                Cookie cookieUser = new Cookie("userEmail", user.getEmail());
                cookieUser.setMaxAge(60*60*24*365);
                response.addCookie(cookieUser);
            }

            // Redirect (Перенаправить) на страницу /userInfo.
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Cookie[] cookies = req.getCookies();
//        String email = null;
//        String password = null;
//
//        for (int i = 0; i < cookies.length; i++) {
//            if (cookies[i].getName().equals("email")) {
//                email = cookies[i].getValue();
//            }
//            if (cookies[i].getName().equals("password")) {
//                password = cookies[i].getValue();
//            }
//        }
//        if (email != null && password != null) {
//            req.setAttribute("email", email);
//            req.setAttribute("password", password);
//        }
//        HttpSession httpSession = req.getSession();

        req.getServletContext().getRequestDispatcher("/login.ftl").forward(req, resp);
    }
}
