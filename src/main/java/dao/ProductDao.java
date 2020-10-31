package dao;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    public List<Product> findAll() throws SQLException;
    public Product findById(int id) throws SQLException;
    public Product findByName(String name) throws SQLException;
    public void insert(Product item) throws SQLException;
    public void update(Product item) throws SQLException;
    public void delete(Product item) throws SQLException;
}
