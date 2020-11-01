package services;

import model.Order;
import model.Product;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    public Order findByIdUser(int idUser, int buyed) throws SQLException;

    public List<Product> findAllProductByOrder(int idOrder) throws SQLException;

    public void insert(Order item) throws SQLException;

    public void insertProduct(int idProduct, int idOrder, int count, String flagId) throws SQLException;

    public void update(Order item) throws SQLException;

    public void delete(Order item) throws SQLException;

    public void deleteProduct(int orderId, int productId, int count) throws SQLException;

    public void createNewOrderAfterBuying(int idOrder, User user) throws SQLException;
}
