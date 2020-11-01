package dao;

import model.*;
import mysql.MySQLConnUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    UserDaoImpl userDao = new UserDaoImpl();
    ProductDaoImpl productDao = new ProductDaoImpl();

    @Override
    public List<Product> findAllProducts(int idOrder) throws SQLException {
        String sql = "SELECT * FROM orderproduct WHERE order_id = ?";
        List<Product> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idOrder);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product res = new Product();
                int idProduct = rs.getInt("product_id");
                int count = rs.getInt("count");
                Product product = productDao.findById(idProduct);
                res.setId(idProduct);
                res.setName(product.getName());
                res.setPrice(product.getPrice() * count);
                res.setCount(count);
                result.add(res);
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
    public Order findById(int id) throws SQLException {
        //language=SQL
        String sql = "SELECT * FROM `order` WHERE id = ?";
        Order order = null;
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int idUser = rs.getInt("user_id");
                User user = userDao.findById(idUser);
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setUser(user);
                order.setTotal_price(rs.getInt("total_price"));
                order.setBuyed(rs.getInt("buyed"));
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
        return order;
    }

    @Override
    public Order findByIdUser(int idUser, int buyed) throws SQLException {
        //language=SQL
        String sql = "SELECT * FROM `order` WHERE user_id = ? AND buyed = ?;";
        Order order = null;
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idUser);
            statement.setInt(2, buyed);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = userDao.findById(idUser);
                order = new Order();
                int idOrder = rs.getInt("id");
                List<Product> productList = findAllProducts(idOrder);
                order.setProductList(productList);
                order.setId(idOrder);
                order.setUser(user);
                order.setBuyed(rs.getInt("buyed"));
                order.setTotal_price(rs.getInt("total_price"));
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
        return order;
    }

    @Override
    public void insert(Order order) throws SQLException {
        //language=SQL
        String sql = "INSERT INTO `order` (user_id, total_price, buyed) VALUES (?, ?, ?);";
        try (Connection connection = MySQLConnUtils.getMySQLConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = 1;
            User user = userDao.findByEmail(order.getUser().getEmail());
            int idUser = user.getId();
            statement.setInt(i++, idUser);
            statement.setInt(i++, order.getTotal_price());
            statement.setInt(i, order.getBuyed());
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProduct(int idProduct, int idOrder, int count, String flagId) throws SQLException {
        //language=SQL
        String sql = "INSERT INTO orderproduct (order_id, product_id, `count`, flag_id) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE `count` = `count` + ?;";
        try (Connection connection = MySQLConnUtils.getMySQLConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = 1;
            Product product = productDao.findById(idProduct);
            int total = findById(idOrder).getTotal_price();
            total += product.getPrice() * count;
            insertTotalPrice(idOrder, total);
            statement.setInt(i++, idOrder);
            statement.setInt(i++, idProduct);
            statement.setInt(i++, count);
            statement.setString(i++, flagId);
            statement.setInt(i, count);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTotalPrice(int idOrder, int totalPrice) {
        //language=SQL
        String sql = "UPDATE `order` SET total_price = ? WHERE id = ?";
        try (Connection connection = MySQLConnUtils.getMySQLConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = 1;
            statement.setInt(i++, totalPrice);
            statement.setInt(i, idOrder);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Order item) throws SQLException {

    }

    @Override
    public void updateBuyed(int idOrder, User user) throws SQLException {
        //language=SQL
        String sql = "UPDATE `order` SET buyed = 1 WHERE id = ?";
        try (Connection connection = MySQLConnUtils.getMySQLConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = 1;
            Order order = new Order();
            order.setUser(user);
            order.setBuyed(0);
            order.setTotal_price(0);
            insert(order);
            statement.setInt(i, idOrder);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int idProduct, int idOrder, int count) throws SQLException {
        //language=SQL
        String sql = "DELETE FROM orderproduct WHERE order_id = ? AND product_id = ?";
        try (Connection connection = MySQLConnUtils.getMySQLConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            int price = productDao.findById(idProduct).getPrice();
            int total = findById(idOrder).getTotal_price();
            total -= price * count;
            insertTotalPrice(idOrder, total);
            int i = 1;
            statement.setInt(i++, idOrder);
            statement.setInt(i, idProduct);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
