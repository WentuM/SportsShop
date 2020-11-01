package dao;

import model.Manufacturer;
import model.Product;
import model.Review;
import mysql.MySQLConnUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private ReviewDaoImpl reviewDao = new ReviewDaoImpl();
    private ManufacturerDaoImpl manufacturerDao = new ManufacturerDaoImpl();

    @Override
    public List<Product> findAll() throws SQLException {
        //language=SQL
        String sql = "SELECT * FROM product";
        List<Product> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));
                product.setImageProduct(rs.getString("imageProduct"));
                product.setCount(rs.getInt("count"));
                product.setCategory(rs.getString("category"));
                result.add(product);
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
    public Product findById(int id) throws SQLException {
        //language=SQL
        String sql = "SELECT * FROM product WHERE id = ?";
        Product product = null;
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int manufactId = rs.getInt("manufacturer_id");
                Manufacturer manufacturer = manufacturerDao.getById(manufactId);
                List<Review> reviewList = reviewDao.getReviewById(id);
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));
                product.setImageProduct(rs.getString("imageProduct"));
                product.setCount(rs.getInt("count"));
                product.setCategory(rs.getString("category"));
                product.setManufacturer(manufacturer);
                product.setList(reviewList);
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
        return product;
    }

    @Override
    public Product findByName(String name) throws SQLException {
        //language=SQL
        String sql = "SELECT * FROM product WHERE `name` = ?";
        Product product = null;
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImageProduct(rs.getString("imageProduct"));
                product.setPrice(rs.getInt("price"));
                product.setCount(rs.getInt("count"));
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
        return product;
    }

    @Override
    public List<Product> findListByName(String name) throws SQLException {
        //language=SQL
        String sql = "SELECT * FROM product WHERE `name` LIKE ?;";
        List<Product> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setImageProduct(rs.getString("imageProduct"));
                product.setCount(rs.getInt("count"));
                result.add(product);
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
    public void insert(Product item) throws SQLException {

    }

    @Override
    public void update(Product product) throws SQLException {
        String sql = "UPDATE product SET `count` = ? WHERE id = ?";
        try (Connection connection = MySQLConnUtils.getMySQLConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = 1;
            Product product1 = findById(product.getId());
            int totalCount = product1.getCount() - product.getCount();
            statement.setInt(i++, totalCount);
            statement.setInt(i, product.getId());
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product item) throws SQLException {

    }
}
