package servlets;

import dao.ProductDaoImpl;
import model.Product;
import services.ProductService;
import services.ProductServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ProductDaoImpl productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("loginedUser") == null) {
            req.setAttribute("auth", "false");
        }
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = null;
        try {
            product = productService.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("product", product);
        req.getRequestDispatcher("/product.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        req.setAttribute("quantity", quantity);
        resp.sendRedirect("/basket");
    }
}
