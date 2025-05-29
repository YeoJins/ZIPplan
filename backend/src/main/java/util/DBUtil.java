package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/zipplan";
            String user = "root";
            String password = "1340";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DB 연결 실패");
        }
    }
}
