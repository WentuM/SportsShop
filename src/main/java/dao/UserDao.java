package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public List<User> findAll() throws SQLException;
    public User findById(int id) throws SQLException;
    public User findByEmail(String email) throws SQLException;
    public void insert(User item) throws SQLException;
    public void update(User item) throws SQLException;
    public void delete(User item) throws SQLException;
}
