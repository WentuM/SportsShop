package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {

    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String hostName = "localhost";
        String dbName = "sport_db";
        String userName = "root";
        String password = "admin";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        // Структура URL Connection для MySQL:
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=Europe/Moscow&useSSL=false";

        return DriverManager.getConnection(connectionURL, userName,
                password);
    }
}
