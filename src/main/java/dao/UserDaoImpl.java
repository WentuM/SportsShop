package dao;

import model.User;
import mysql.MySQLConnUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO{
    private DataSource dataSource;

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM user ";
        List<User> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                result.add(user);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    @Override
    public User findById(Long id) throws SQLException {
        String sql = "Select a, from user a "//
                + " where a.id = ? ";
        User user = null;
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.getEmail(rs.getString("email"));
                user.getPassword(rs.getString("password"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return user;
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        String sql = "Select a.email, from user a "//
                + " where a.email = ? ";
        User user = null;
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.getEmail(rs.getString("email"));
                user.getPassword(rs.getString("password"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return user;
    }

    @Override
    public void insert(User item) throws SQLException {

    }

    @Override
    public void update(User item) throws SQLException {

    }

    @Override
    public void delete(User item) throws SQLException {

    }
}
