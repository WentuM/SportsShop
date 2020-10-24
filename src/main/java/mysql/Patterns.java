package mysql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patterns {
    public static String pattern(String name, String number, String password, String email) {
        if (!patt(email, "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            return ("Почтовый ящик введён некорректно, пример: example@mail.ru");
        } else if (!patt(name, "/^[а-яА-Я]{20}|[a-zA-Z]{20}$/")) {
            return ("Имя должно содержать только русские или только английские буквы, максимальное чилсо введенных символов 20");
        } else if (!patt(number, "^(8|+7)+[0-9]{10}")) {
            return ("Телефон должен начинаться на 8 или +7, максимальное число последующих цифр 10");
        } else if (!patt(password, "/(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}/g")) {
            return "Пароль должен состоять из не менее, чем 6 символов, при этом пароль содержит хотя бы одно число, латинскую букву в нижнем регистре и в верхнем регистре";
        }
        return "ok";
    }

    private static boolean patt(String expression, String pattern) {
        Pattern pattern1 = Pattern.compile(pattern);
        Matcher matcher = pattern1.matcher(expression);
        return matcher.matches();
    }
}
