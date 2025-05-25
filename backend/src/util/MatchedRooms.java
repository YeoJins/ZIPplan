package util;

import java.sql.*;

public class MatchedRooms {

    public static void main(String[] args) {
        int userId = 1; // 테스트할 사용자 ID

        try (Connection conn = DBUtil.getConnection()) {

            // 뷰에서 조건에 맞는 매물 조회
            String query = "SELECT * FROM matched_rooms_view WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();

                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("🏠 매물 ID: " + rs.getInt("room_id"));
                    System.out.println(" - 건물명: " + rs.getString("building_name"));
                    System.out.println(" - 위치: " + rs.getString("gu_name") + " " + rs.getString("dong_name"));
                    System.out.println(" - 방 번호: " + rs.getInt("room_num") + ", 층: " + rs.getInt("floor"));
                    System.out.println(" - 보증금: " + rs.getInt("deposit") + "만원");
                    System.out.println(" - 월세: " + rs.getInt("monthly_rent") + "만원");
                    System.out.println("-----------");
                }

                if (!found) {
                    System.out.println("❌ 해당 조건에 맞는 매물이 없습니다.");
                }

                rs.close();
            }

        } catch (SQLException e) {
            System.err.println("DB 오류:");
            e.printStackTrace();
        }
    }
}