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
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/basket"})
public class BasketServlet extends HttpServlet {
    private String errorMessage = "";
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
            if (order != null) {
                req.setAttribute("orderId", order.getId());
                req.setAttribute("allprice", order.getTotal_price());
            }
            req.setAttribute("errorMessage", errorMessage);
            errorMessage = "";
            req.setAttribute("products", list);
            req.getRequestDispatcher("/basket.ftl").forward(req, resp);
        } else {
            HashMap<String, Product> hashMap = new HashMap<>();
            Product product = null;
            Cookie[] cookies = req.getCookies();
            int totalPrice = 0;
            for (Cookie cookie : cookies) {
                String[] strings = cookie.getValue().split("-");
                if (strings.length == 3) {
                    product = new Product();
                    product.setName(strings[0]);
                    product.setPrice(Integer.parseInt(strings[1]));
                    product.setCount(Integer.parseInt(strings[2]));
                    totalPrice += Integer.parseInt(strings[1]);
                    hashMap.put(cookie.getName(), product);
                }
            }
            req.setAttribute("products", hashMap);
            req.setAttribute("allprice", totalPrice);
            req.getRequestDispatcher("/basketCookie.ftl").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("loginedUser");
        if (email == null) {
            resp.sendRedirect("/login");
        } else {
            User user = null;
            Order order = null;
            List<Product> productList = null;
            try {
                user = usersService.findByEmail(email);
                order = orderService.findByIdUser(user.getId(), 0);
                productList = orderService.findAllProductByOrder(order.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (order != null && order.getTotal_price() == 0) {
                errorMessage = "Добавьте в заказ что-нибудь";
                resp.sendRedirect("/basket");
                return;
            }
            for (Product product : productList) {
                Product product1 = null;
                try {
                    product1 = productService.findById(product.getId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (product.getCount() > product1.getCount()) {
                    errorMessage = "На данный момент количество товара  " + product1.getName() + ":  " + product1.getCount();
                }
                if (!errorMessage.equals("")) {
                    resp.sendRedirect("/basket");
                    return;
                }
            }
            resp.sendRedirect("/order");
        }
    }
}
