package servlets;

import dao.OrderDaoImpl;
import dao.ProductDaoImpl;
import dao.UserDaoImpl;
import model.Product;
import services.OrderServiceImpl;
import services.ProductServiceImpl;
import services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("loginedUser");
        if (email != null) {
            HashMap<String, Product> hashMap = new HashMap<>();
            Product product = null;
            Cookie[] cookies = req.getCookies();
            int totalPrice = 0;
            for (Cookie cookie : cookies) {
                String[] strings = cookie.getValue().split("-");
                if (strings.length == 3) {
                    int idProduct = 0;
                    int idOrder = 0;
                    int idUser = 0;
                    try {
                        idUser = usersService.findByEmail(email).getId();
                        idProduct = productService.findByName(strings[0]).getId();
                        idOrder = orderService.findByIdUser(idUser, 0).getId();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String flagId = "" + idOrder + idProduct;
                    try {
                        orderService.insertProduct(idProduct, idOrder, Integer.parseInt(strings[2]), flagId);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    Cookie cookie1 = new Cookie(cookie.getName(), "deleteCookie");
                    cookie1.setMaxAge(1);
                    resp.addCookie(cookie1);
                }

            }
        }
        req.getRequestDispatcher("/main.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
