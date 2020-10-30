package services;

import model.Review;

import java.sql.SQLException;
import java.util.List;

public interface ReviewService {
    public List<Review> findAll() throws SQLException;
    public List<Review> allReviewsByProduct(int id) throws SQLException;
    public void addReview(Review review) throws SQLException;
}
