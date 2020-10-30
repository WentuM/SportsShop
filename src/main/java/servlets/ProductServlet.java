package servlets;

import dao.ProductDaoImpl;
import dao.ReviewDaoImpl;
import model.Product;
import model.Review;
import services.ProductService;
import services.ProductServiceImpl;
import services.ReviewService;
import services.ReviewServiceImpl;

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

@WebServlet(urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    private ProductService productService;
    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ProductDaoImpl productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        reviewService = new ReviewServiceImpl(reviewDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("loginedUser") == null) {
            req.setAttribute("auth", "false");
        }
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = null;
        List<Review> reviewList = null;
        try {
            product = productService.findById(id);
            reviewList = reviewService.allReviewsByProduct(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("reviews", reviewList);
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
