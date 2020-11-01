package services;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    public List<Product> findAll() throws SQLException;
    public Product findById(int id) throws SQLException;
    public Product findByName(String name) throws SQLException;
    public void updateCountProduct(Product product) throws SQLException;
    public List<Product> searchByName(String name) throws SQLException;
    public List<Product> searchByCategory(String name) throws SQLException;
    public List<Product> searchByCategoryAndName(String name, String category) throws SQLException;
}
