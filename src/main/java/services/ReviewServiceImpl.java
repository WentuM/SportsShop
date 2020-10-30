package services;

import dao.ReviewDao;
import model.Review;

import java.sql.SQLException;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewDao reviewDao;

    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
    @Override
    public List<Review> findAll() throws SQLException {
        return reviewDao.getAll();
    }

    @Override
    public List<Review> allReviewsByProduct(int id) throws SQLException {
        return reviewDao.getReviewById(id);
    }

    @Override
    public void addReview(Review review) throws SQLException {
        reviewDao.insert(review);
    }
}
