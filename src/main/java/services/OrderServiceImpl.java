package services;

import dao.OrderDao;
import model.Order;
import model.Product;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order findByIdUser(int idUser, int buyed) throws SQLException {
        return orderDao.findByIdUser(idUser, buyed);
    }

    @Override
    public List<Product> findAllProductByOrder(int idOrder) throws SQLException {
        return orderDao.findAllProducts(idOrder);
    }

    @Override
    public void insert(Order item) throws SQLException {
        orderDao.insert(item);
    }

    @Override
    public void insertProduct(int idProduct, int idOrder, int count, String flagId) throws SQLException {
        orderDao.insertProduct(idProduct, idOrder, count, flagId);
    }

    @Override
    public void update(Order item) throws SQLException {

    }

    @Override
    public void delete(Order item) throws SQLException {

    }

    @Override
    public void deleteProduct(int orderId, int productId, int count) throws SQLException {
        orderDao.deleteProduct(orderId, productId, count);
    }

    @Override
    public void createNewOrderAfterBuying(int idOrder, User user) throws SQLException {
        orderDao.updateBuyed(idOrder, user);
    }
}
