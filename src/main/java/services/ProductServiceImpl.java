package services;

import dao.ProductDao;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }
    @Override
    public List<Product> findAll() throws SQLException {
        return productDao.findAll();
    }

    @Override
    public Product findById(int id) throws SQLException {
        return productDao.findById(id);
    }

    @Override
    public Product findByName(String name) throws SQLException {
        return productDao.findByName(name);
    }

    @Override
    public void updateCountProduct(Product product) throws SQLException {
        productDao.update(product);
    }

    @Override
    public List<Product> searchByName(String name) throws SQLException {
        return productDao.findListByName(name);
    }

    @Override
    public List<Product> searchByCategory(String name) throws SQLException {
        return productDao.findListByCategory(name);
    }

    @Override
    public List<Product> searchByCategoryAndName(String name, String category) throws SQLException {
        return productDao.findListByCategoryAndName(name, category);
    }
}
