package orm.caster;

import orm.annotation.ManyToOne;
import orm.utils.SqlUtils;

import java.lang.reflect.Field;

public class ManyToOneCaster implements Castable {
    @Override
    public String cast(Field field) {
        return "`" + field.getName() + "` INT, FOREIGN KEY (`" + field.getName() + "`)" + SqlUtils.recognizeConstraints(field) + " " + SqlUtils.recognizeForeignKey(field);
    }

    @Override
    public boolean isSupport(Field field) {
        return field.getAnnotation(ManyToOne.class) != null;
    }
}
