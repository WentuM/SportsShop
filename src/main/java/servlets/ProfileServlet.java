package servlets;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null) {
            Object flag = session.getAttribute("loginedUser");
            if (flag == null) {
                resp.sendRedirect("/login");
            } else if (!flag.toString().equals("null")) {
                req.getRequestDispatcher("/profile.ftl").forward(req, resp);
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginedUser");
        super.doGet(req, resp);
    }
}
