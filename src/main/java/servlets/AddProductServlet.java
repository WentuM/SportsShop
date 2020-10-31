package servlets;

import dao.OrderDaoImpl;
import dao.UserDaoImpl;
import model.Order;
import model.User;
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

@WebServlet(urlPatterns = {"/addProduct"})
public class AddProductServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int idProduct = Integer.parseInt(req.getParameter("id"));
        int count = Integer.parseInt(req.getParameter("quantity"));
        String email = (String) session.getAttribute("loginedUser");
        if (email != null) {
            try {
                User user = usersService.findByEmail(email);
                Order order = orderService.findByIdUser(user.getId(), 0);
                orderService.insertProduct(idProduct, order.getId() ,count);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        int id = Integer.parseInt(req.getParameter("id"));
        resp.sendRedirect("/product?id=" + id);
    }
}
