package dao;

import model.Review;

import java.util.List;

public interface ReviewDao {
    public List<Review> getAll();
    public List<Review> getReviewById(int productId);
    public void insert(Review review);
}
