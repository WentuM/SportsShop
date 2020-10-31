package services;

import dao.OrderDao;
import model.Order;
import model.Product;

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
    public void insertProduct(int idProduct, int idOrder, int count) throws SQLException {
        orderDao.insertProduct(idProduct, idOrder, count);
    }

    @Override
    public void update(Order item) throws SQLException {

    }

    @Override
    public void delete(Order item) throws SQLException {

    }
}
