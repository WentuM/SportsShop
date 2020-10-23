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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/register"})
public class RegistrationServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DataSource dataSource = (DataSource) config.getServletContext().getAttribute("datasource");
        UserDaoImpl userDao = new UserDaoImpl();
        usersService = new UsersServiceImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/registration.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        User user = null;
        boolean hasError = false;
        String errorString = null;
        boolean regularEmail = email.matches("");
        boolean regularPassword = email.matches("");

        if (email == null || email.length() == 0 || name == null || name.length() == 0 ||
                password == null || password.length() == 0 || number == null || number.length() == 0) {
            hasError = true;
            errorString = "Заполните все поля формы регистрации";
        } else {
            try {
                Connection conn = MySQLConnUtils.getMySQLConnection();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            try {
                // Найти user в DB.
                user = usersService.findByEmail(email);

                if (user != null) {
                    hasError = true;
                    errorString = "Пользователь с такой почтой уже существует";
                }

            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
//                 В случае, если есть ошибка,
//                 forward (перенаправить) к /WEB-INF/views/login.jsp
        if (hasError) {

            // Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);

            // Forward (перенаправить) к странице /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/register");
            dispatcher.forward(request, response);
        }
        // В случае, если нет ошибки.
        // Сохранить информацию пользователя в Session.
        // И перенаправить к странице userInfo.
        else {
            HttpSession session = request.getSession();
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setNumber(number);
//                    user.setId();
            session.setAttribute("loginedUser", user);
//            session.setAttribute("userId", user.getId());

            // Redirect (Перенаправить) на страницу /userInfo.
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
