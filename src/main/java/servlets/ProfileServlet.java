package servlets;

import dao.UserDaoImpl;
import model.User;
import services.UsersService;
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

@WebServlet(urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    private UsersService usersService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        UserDaoImpl userDao = new UserDaoImpl();
        usersService = new UsersServiceImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("loginedUser") != null) {
            Object flag = session.getAttribute("loginedUser");
            if (flag == null) {
                resp.sendRedirect("/login");
            } else  {
                String email = (String) session.getAttribute("loginedUser");
                User user = null;
                try {
                    user = usersService.findByEmail(email);
                } catch (SQLException e) {
                    throw new IllegalStateException(e);
                }
                req.setAttribute("name", user.getName());
                req.setAttribute("number", user.getNumber());
                req.setAttribute("email", user.getEmail());
                req.getRequestDispatcher("/profile.ftl").forward(req, resp);
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
