package util;

import util.DBUtil;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil.getConnection();
            System.out.println("✅ DB 연결 성공!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ DB 연결 실패:");
            e.printStackTrace();
        }
    }
}
