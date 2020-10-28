package dao;

import model.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    public List<Order> findAll() throws SQLException;
    public Order findById(Long id) throws SQLException;
    public void insert(Order item) throws SQLException;
    public void update(Order item) throws SQLException;
    public void delete(Order item) throws SQLException;
}
