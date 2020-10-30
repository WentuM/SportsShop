package servlets;

import dao.ReviewDaoImpl;
import dao.UserDaoImpl;
import model.Review;
import services.ReviewServiceImpl;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = {"/addReview"})
public class ReviewServlet extends HttpServlet {
    private ReviewServiceImpl reviewService;
    private UsersServiceImpl usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        reviewService = new ReviewServiceImpl(reviewDao);
        UserDaoImpl userDao = new UserDaoImpl();
        usersService = new UsersServiceImpl(userDao);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        Review review = new Review();
        String reviewText = req.getParameter("reviewText");
        int id = Integer.parseInt(req.getParameter("id"));
        review.setText(reviewText);
        review.setData(dtf.format(localDate));
        review.setProduct_id(id);
        try {
            review.setUser(usersService.findByEmail((String) session.getAttribute("loginedUser")));
            reviewService.addReview(review);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("/product?id=" + id);
    }
}
