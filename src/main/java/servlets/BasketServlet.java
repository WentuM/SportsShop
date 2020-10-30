package servlets;

import dao.OrderDaoImpl;
import dao.UserDaoImpl;
import model.Order;
import services.OrderServiceImpl;
import services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/basket"})
public class BasketServlet extends HttpServlet {
    private OrderServiceImpl orderService;
    private UsersServiceImpl usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderService = new OrderServiceImpl(orderDao);
        UserDaoImpl userDao = new UserDaoImpl();
        usersService = new UsersServiceImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("loginedUser");
        Order order = null;
        int idUser = 0;
        if (email != null) {
            try {
                idUser = usersService.findByEmail(email).getId();
                order = orderService.findByIdUser(idUser);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        req.setAttribute("order", order);
        req.getServletContext().getRequestDispatcher("/basket.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
