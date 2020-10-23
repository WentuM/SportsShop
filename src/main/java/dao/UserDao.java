package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public static final String SQL_FIND_ALL = "select * from " + User.TABLE_NAME;
    public static final String SQL_FIND_BY_ID = SQL_FIND_ALL + " where " + User.ID_COLUMN + " = ?";
    public static final String SQL_FIND_BY_NAME = SQL_FIND_ALL + " where " + User.NAME_COLUMN + " = ?";
    public static final String SQL_INSERT = "insert into " + User.TABLE_NAME + " (" + User.NAME_COLUMN + ", " + User.PASSWORD_COLUMN + ") values (?, ?)";
    public static final String SQL_UPDATE = "update " + User.TABLE_NAME + " set " + User.NAME_COLUMN + " where " + User.ID_COLUMN + " = ?";
    public static final String SQL_DELETE = "delete from " + User.TABLE_NAME + " where " + User.ID_COLUMN + " = ?";

    public List<User> findAll() throws SQLException;
    public User findById(Long id) throws SQLException;
    public User findByEmail(String email) throws SQLException;
    public void insert(User item) throws SQLException;
    public void update(User item) throws SQLException;
    public void delete(User item) throws SQLException;
}
