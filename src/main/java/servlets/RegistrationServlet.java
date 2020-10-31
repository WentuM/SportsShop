package servlets;

import dao.OrderDaoImpl;
import dao.UserDaoImpl;
import model.Order;
import model.User;
import mysql.PaswordHash;
import mysql.Patterns;
import services.OrderService;
import services.OrderServiceImpl;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/register"})
public class RegistrationServlet extends HttpServlet {
    private UsersService usersService;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        UserDaoImpl userDao = new UserDaoImpl();
        usersService = new UsersServiceImpl(userDao);
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderService = new OrderServiceImpl(orderDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registration.ftl").forward(request, response);
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

        if (email == null || email.length() == 0 || name == null || name.length() == 0 ||
                password == null || password.length() == 0 || number == null || number.length() == 0) {
            hasError = true;
            errorString = "Заполните все поля формы регистрации";
        }
        String check = Patterns.pattern(name, number, password, email);
        if (check.equals("ok")) {
            try {
                if (email != null) {
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
            request.getServletContext().getRequestDispatcher("/registration.ftl").forward(request, response);

        } else {
            Order order = new Order();
            password = PaswordHash.hash(password.trim());
            user = new User();
            user.setName(name);
            user.setNumber(number);
            user.setPassword(password);
            user.setEmail(email);
            user.setImage("user.png");
            order.setUser(user);
            order.setBuyed(0);
            order.setTotal_price(0);
            try {
                usersService.createUser(user);
                orderService.insert(order);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }

            response.sendRedirect("/login");
        }
    }
}
