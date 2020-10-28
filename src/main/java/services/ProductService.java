package services;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    public List<Product> findAll() throws SQLException;
    public Product findById(int id) throws SQLException;
}
