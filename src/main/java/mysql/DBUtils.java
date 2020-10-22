//package mysql;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class DBUtils {
//    public static UserAccount findUser(Connection conn, //
//                                       String userName, String password) throws SQLException {
//
//
//        //language=SQL
//        String sql = "Select a.email, a.password, from user a where a.email = ? and a.password= ?";
//
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, userName);
//        pstm.setString(2, password);
//        ResultSet rs = pstm.executeQuery();
//
//        if (rs.next()) {
//            String gender = rs.getString("Gender");
//            UserAccount user = new UserAccount();
//            user.setUserName(userName);
//            user.setPassword(password);
//            user.setGender(gender);
//            return user;
//        }
//        return null;
//    }
//
//    public static boolean findUserRegistration(Connection conn, String email) throws SQLException {
//
//        String sql = "Select a.email, from user a "//
//                + " where a.email = ? ";
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, email);
//        ResultSet rs = pstm.executeQuery();
//
//        if (rs.next()) {
//            UserAccount user = new UserAccount();
//            user.setEmail(email);
//            return false;
//        }
//
//        return true;
//    }
//
//    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
//
//        String sql = "Select a.email, a.password from user a "//
//                + " where a.email = ? ";
//
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, userName);
//
//        ResultSet rs = pstm.executeQuery();
//
//        if (rs.next()) {
//            String password = rs.getString("Password");
//            String gender = rs.getString("Gender");
//            UserAccount user = new UserAccount();
//            user.setUserName(userName);
//            user.setPassword(password);
//            user.setGender(gender);
//            return user;
//        }
//        return null;
//    }
//}
