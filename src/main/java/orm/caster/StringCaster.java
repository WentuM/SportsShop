package orm.caster;

import orm.utils.SqlUtils;

import java.lang.reflect.Field;

public class StringCaster implements Castable{
    @Override
    public String cast(Field field) {
        return "`" + field.getName() + "` VARCHAR(500) " + SqlUtils.recognizeConstraints(field);
    }

    @Override
    public boolean isSupport(Field field) {
        return field.getType().getSimpleName().equals("String");
    }
}
