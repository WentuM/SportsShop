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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/order"})
public class OrderingSerlvet extends HttpServlet {
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
        User user = null;
        List<Product> productList = null;
        int idOrder = 0;
        try {
            user = usersService.findByEmail((String) session.getAttribute("loginedUser"));
            idOrder = orderService.findByIdUser(user.getId(), 0).getId();
            orderService.createNewOrderAfterBuying(idOrder ,user);
            productList = orderService.findAllProductByOrder(idOrder);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Product product: productList) {
            try {
                productService.updateCountProduct(product);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        resp.sendRedirect("/main?idOrder=" + idOrder);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = null;
        int price = 0;
        Order order = null;
        try {
            user = usersService.findByEmail((String) session.getAttribute("loginedUser"));
            order = orderService.findByIdUser(user.getId(), 0);
            price = order.getTotal_price();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (price > 2000) {
            price -= 200;
        }
        if (user != null && order != null) {
            req.setAttribute("nameUser", user.getName());
            req.setAttribute("numberUser", user.getNumber());
            req.setAttribute("price", price);
        }
        req.getServletContext().getRequestDispatcher("/ordering.ftl").forward(req, resp);
    }
}
