package dao;

import model.User;
import mysql.MySQLConnUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private DataSource dataSource;

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM user; ";
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
        } catch (IllegalAccessException | InstantiationException e) {
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
    public User findById(Long id) throws SQLException {
        String sql = "SELECT a, FROM user a "//
                + " WHEN a.id = ?;";
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
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
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
        //language=SQL
        String sql = "SELECT a.email, FROM user a "//
                + " WHERE a.email = ?;";
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
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
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
        //language=SQL
        String sql = "INSERT INTO User(name, number, id, password, email) values (?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setString(2, item.getNumber());
            statement.setLong(3, item.getId());
            statement.setString(4, item.getPassword());
            statement.setString(5, item.getEmail());
            statement.execute();

            ResultSet generatedkeys = statement.getGeneratedKeys();
            if (generatedkeys.next()) {
                item.setId(generatedkeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    @Override
    public void update(User item) throws SQLException {

    }

    @Override
    public void delete(User item) throws SQLException {

    }
}
