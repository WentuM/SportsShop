package dao;

import model.Manufacturer;
import mysql.MySQLConnUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer getById(int id) {
        String sql = "SELECT * FROM manufacturer WHERE id = ?";
        Manufacturer result = new Manufacturer();
        Connection connection = null;
        try {
            connection = MySQLConnUtils.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.setName(rs.getString("name"));
                result.setDescription(rs.getString("text"));
                result.setManufacturerImage(rs.getString("imageManufact"));
                result.setId(id);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return result;
    }
}
