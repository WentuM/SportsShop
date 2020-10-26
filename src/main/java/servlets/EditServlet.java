package servlets;

import dao.UserDaoImpl;
import model.User;
import mysql.Patterns;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/editProfile"})
public class EditServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        UserDaoImpl userDao = new UserDaoImpl();
        usersService = new UsersServiceImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("loginedUser") != null) {
            String email = (String) session.getAttribute("loginedUser");
            User user = null;
            try {
                user = usersService.findByEmail(email);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
            req.setAttribute("name", user.getName());
            req.setAttribute("number", user.getNumber());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/edit.ftl").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailUser = (String) session.getAttribute("loginedUser");
        int idUser = 0;
        String nameUser = "";
        String numberUser = "";
        User us = null;
        try {
            us = usersService.findByEmail(emailUser);
            idUser = us.getId();
            nameUser = us.getName();
            numberUser = us.getNumber();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(idUser);
        System.out.println(nameUser);
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        System.out.println(name);
        System.out.println(email);
        System.out.println(emailUser);
        User user = null;
        boolean hasError = false;
        String errorString = null;
        if (email == null || email.length() == 0 || name == null || name.length() == 0 ||
                number == null || number.length() == 0) {
            hasError = true;
            errorString = "Заполните все поля формы регистрации";
        }
        String check = Patterns.pattern(name, number, email);
        if (check.equals("ok")) {
            try {
                if (email != null && (!email.equals(emailUser))) {
                    user = usersService.findByEmail(email.trim());
                }

                if (user != null) {
                    hasError = true;
                    errorString = "Пользователь с такой почтой уже существует";
                }

            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        } else {
            errorString = check;
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("errorString", errorString);
            request.getServletContext().getRequestDispatcher("/edit.ftl").forward(request, response);

        } else {
            user = new User();
            if (name.equals(nameUser)) {
                user.setName(nameUser);
            } else {
                user.setName(name);
            }
            if (number.equals(numberUser)) {
                user.setNumber(numberUser);
            } else {
                user.setNumber(number);
            }
            if (email.equals(emailUser)) {
                user.setEmail(emailUser);
            } else {
                user.setEmail(email);
                session.setAttribute("loginedUser", email);
                Cookie cookieUser = new Cookie("userEmail", email);
                cookieUser.setMaxAge(60*60*24*365);
                response.addCookie(cookieUser);
            }
            user.setId(idUser);
            user.setPassword(us.getPassword());
            try {
                usersService.update(user);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }

            response.sendRedirect("/profile");
        }
    }
}
