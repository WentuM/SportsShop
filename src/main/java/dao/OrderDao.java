package dao;

import model.Order;
import model.Product;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    public List<Product> findAllProducts(int idOrder) throws SQLException;

    public Order findById(int id) throws SQLException;

    public Order findByIdUser(int idUser, int buyed) throws SQLException;

    public void insert(Order order) throws SQLException;

    public void insertProduct(int idProduct, int idOrder, int count, String flagId) throws SQLException;

    public void insertTotalPrice(int idOrder, int totalPrice);

    public void delete(Order item) throws SQLException;

    public void updateBuyed(int idOrder, User user) throws SQLException;

    public void deleteProduct(int idProduct, int idOrder, int count) throws SQLException;


}
