package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/zipplan?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "dustn0926"; // ← 네 DB 비번으로 수정!

        return DriverManager.getConnection(url, user, password);
    }
}
