package dao;

import model.Review;
import model.User;
import mysql.MySQLConnUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    private UserDaoImpl userDao = new UserDaoImpl();
    @Override
    public List<Review> getAll() {
        String sql = "SELECT * FROM review";
        List<Review> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                User user = userDao.findById(id);
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setUser(user);
                review.setData(rs.getString("data"));
                review.setProduct_id(rs.getInt("product_id"));
                review.setText(rs.getString("text"));
                result.add(review);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return result;
    }

    public List<Review> getReviewById(int productId) {
        String sql = "SELECT * FROM review WHERE product_id = ?";
        List<Review> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                User user = userDao.findById(id);
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setUser(user);
                review.setData(rs.getString("data"));
                review.setProduct_id(rs.getInt("product_id"));
                review.setText(rs.getString("text"));
                result.add(review);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return result;
    }

    @Override
    public void insert(Review review) {
        //language=SQL
        String sql = "INSERT INTO review (text, data, user_id, product_id) VALUES (?, ?, ?, ?);";
        try (Connection connection = MySQLConnUtils.getMySQLConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = 1;
            statement.setString(i++, review.getText());
            statement.setString(i++, review.getData());
            statement.setInt(i++, review.getUser().getId());
            statement.setInt(i, review.getProduct_id());
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
