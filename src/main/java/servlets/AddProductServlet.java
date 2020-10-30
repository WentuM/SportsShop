package servlets;

import dao.OrderDaoImpl;
import dao.UserDaoImpl;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int idProduct = (int) req.getAttribute("id");
        int count = Integer.parseInt(req.getParameter("count"));
        String email = (String) session.getAttribute("loginedUser");
        if (email != null) {
            try {
                User user = usersService.findByEmail(email);
                orderService.insertProduct(idProduct, user.getId() ,count);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        int id = Integer.parseInt(req.getParameter("id"));
        resp.sendRedirect("/product?id=" + id);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
