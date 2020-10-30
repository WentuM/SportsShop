package services;

import model.Order;

import java.sql.SQLException;

public interface OrderService {

    public Order findByIdUser(int idUser) throws SQLException;

    public void insert(Order item) throws SQLException;

    public void insertProduct(int idProduct, int idOrder, int count) throws SQLException;

    public void update(Order item) throws SQLException;

    public void delete(Order item) throws SQLException;
}
