//package addDataBase;
//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//public class Program {
//    public static void main(String[] args) {
//        try {
//            String url = "jdbc:mysql://localhost:3306/sport_db?serverTimezone=Europe/Moscow&useSSL=false";
//            String username = "root";
//            String password = "admin";
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            try (Connection connection = DriverManager.getConnection(url, username, password)) {
//                Statement statement = connection.createStatement();
//                int rows = statement.executeUpdate("INSERT user(username, email, password) VALUES ('Danil', 'danil@mail.ru', 123)");
//                System.out.printf("Added %d rows", rows);
//            }
//        }
//        catch (Exception ex) {
//            System.out.println("Connection failed...");
//
//            System.out.println(ex);
//        }
//    }
//}
