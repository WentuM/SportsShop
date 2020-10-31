package servlets;

import dao.OrderDaoImpl;
import dao.UserDaoImpl;
import model.Order;
import model.Product;
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
import java.util.List;

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
        int buyed = 0;
        List<Product> list = null;
        if (email != null) {
            try {
                idUser = usersService.findByEmail(email).getId();
                order = orderService.findByIdUser(idUser, buyed);
                list = orderService.findAllProductByOrder(order.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        req.setAttribute("products", list);
        req.getServletContext().getRequestDispatcher("/basket.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
