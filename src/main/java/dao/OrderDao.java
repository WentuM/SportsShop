package dao;

import model.Order;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    public List<Product> findAllProducts(int idOrder) throws SQLException;

    public Order findById(int id) throws SQLException;

    public Order findByIdUser(int idUser) throws SQLException;

    public void insert(Order order) throws SQLException;

    public void insertProduct(int idProduct, int idOrder, int count) throws SQLException;

    public void update(Order item) throws SQLException;

    public void delete(Order item) throws SQLException;

}
