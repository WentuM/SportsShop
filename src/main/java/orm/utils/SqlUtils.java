package orm.utils;

import orm.annotation.Constraint;
import orm.annotation.Table;

import java.lang.reflect.Field;

public class SqlUtils {
    public static String recognizeConstraints(Field field){
        StringBuilder sql = new StringBuilder();
        Constraint constraint = field.getAnnotation(Constraint.class);
        if (constraint != null){
            if (constraint.pk()) sql.append(" PRIMARY KEY ");
            if (constraint.notNull()) sql.append(" NOT NULL ");
            if (constraint.autoInc()) sql.append(" AUTO_INCREMENT ");
            if (constraint.unic()) sql.append(" UNIQUE ");
        }
        return sql.toString();
    }

    public static String recognizeForeignKey(Field field) {
        return " REFERENCES " + recognizeTableName(field.getType()) + "(id)";
    }

    public static String recognizeTableName(Class<?> table) {
        String tableName;
        Table annotation = table.getAnnotation(Table.class);
        if (annotation.name().isEmpty()){
            tableName = table.getSimpleName();
        } else {
            tableName = annotation.name();
        }
        return tableName;
    }
}
