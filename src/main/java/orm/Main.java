package orm;

import orm.utils.DBCreator;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        DBCreator dbCreator = new DBCreator();
        dbCreator.create();

    }
}
