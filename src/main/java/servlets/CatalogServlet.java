package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProductDaoImpl;
import model.Product;
import org.json.JSONObject;
import services.ProductService;
import services.ProductServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/catalog"})
public class CatalogServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ProductDaoImpl productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productService.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("products", productList);
        req.getServletContext().getRequestDispatcher("/catalog.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = null;
        String strJson = req.getReader().lines().collect(Collectors.joining());
        JSONObject jsonObject = new JSONObject(strJson.trim());
        String search = jsonObject.getString("search");

        if (search != null) {
            try {
                list = productService.searchByName(search);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        String jsonResponse = objectMapper.writeValueAsString(list);
        resp.setContentType("application/json");
        resp.getWriter().println(jsonResponse);
    }
}
