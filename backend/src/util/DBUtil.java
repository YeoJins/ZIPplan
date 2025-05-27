package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {

         try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // 💥 이 줄 추가해줘!
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    
        String url = "jdbc:mysql://localhost:3306/zipplan?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "1340"; // ← 네 DB 비번으로 수정!

        return DriverManager.getConnection(url, user, password);
    }
}
