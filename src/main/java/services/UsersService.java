package services;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersService {
    public List<User> findAll() throws SQLException;
    public User findById(int id) throws SQLException;
    public User findByEmail(String email) throws SQLException;
    public void createUser(User item) throws SQLException;
    public void update(User item) throws SQLException;
    public void delete(User item) throws SQLException;
}
