package services;

import dao.UserDao;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UsersServiceImpl implements UsersService {
    private final UserDao userDao;

    public UsersServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() throws SQLException {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) throws SQLException {
        return userDao.findById(id);
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        return userDao.findByEmail(email);
    }

    @Override
    public void insert(User item) throws SQLException {

    }

    @Override
    public void update(User item) throws SQLException {

    }

    @Override
    public void delete(User item) throws SQLException {

    }
}
