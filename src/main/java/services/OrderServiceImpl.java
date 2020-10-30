package services;

import dao.OrderDao;
import model.Order;

import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order findByIdUser(int idUser) throws SQLException {
        return orderDao.findByIdUser(idUser);
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
