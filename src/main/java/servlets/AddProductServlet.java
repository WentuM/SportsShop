package servlets;

import dao.OrderDaoImpl;
import dao.ProductDaoImpl;
import dao.UserDaoImpl;
import model.Order;
import model.Product;
import model.User;
import services.OrderServiceImpl;
import services.ProductServiceImpl;
import services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/addProduct"})
public class AddProductServlet extends HttpServlet {
    private OrderServiceImpl orderService;
    private UsersServiceImpl usersService;
    private ProductServiceImpl productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderService = new OrderServiceImpl(orderDao);
        UserDaoImpl userDao = new UserDaoImpl();
        usersService = new UsersServiceImpl(userDao);
        ProductDaoImpl productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
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
                String flagId = "" + order.getId() + idProduct;
                orderService.insertProduct(idProduct, order.getId(), count, flagId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            String nameProduct = "";
            int price = 0;
            try {
                Product product = productService.findById(idProduct);
                nameProduct = product.getName();
                price = product.getPrice() * count;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Cookie cookie = new Cookie(UUID.randomUUID().toString(), "" + nameProduct + "-" + price + "-" + count);
            cookie.setMaxAge(60 * 60 * 24 * 365);
            resp.addCookie(cookie);
        }
        int id = Integer.parseInt(req.getParameter("id"));
        resp.sendRedirect("/product?id=" + id);
    }
}
