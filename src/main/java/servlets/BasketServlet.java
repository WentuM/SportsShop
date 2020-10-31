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
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
            if (order != null) {
                req.setAttribute("orderId", order.getId());
                req.setAttribute("allprice", order.getTotal_price());
            }
            req.setAttribute("products", list);
            req.getServletContext().getRequestDispatcher("/basket.ftl").forward(req, resp);
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
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("loginedUser");
        if (email == null) {
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/order");
        }
    }
}
