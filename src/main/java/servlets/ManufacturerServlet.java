package servlets;

import dao.ManufacturerDaoImpl;
import services.ManufacturerServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/manufacturer"})
public class ManufacturerServlet extends HttpServlet {
    private ManufacturerServiceImpl manufacturerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ManufacturerDaoImpl manufacturerDao = new ManufacturerDaoImpl();
        manufacturerService = new ManufacturerServiceImpl(manufacturerDao);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int manufacturer = Integer.parseInt(req.getParameter("manufacturer"));
        req.setAttribute("manufactur", manufacturerService.findManufactById(manufacturer));
        req.getRequestDispatcher("manufacturer.ftl").forward(req, resp);
    }
}
