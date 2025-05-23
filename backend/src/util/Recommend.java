package util;

import java.sql.*;

public class Recommend {

    public static void main(String[] args) {
        int userId = 5; // 테스트용 유저 ID. 실제 서비스에선 로그인 유저의 ID로 대체해야 함.

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT r.room_id, r.deposit, r.monthly_rent, r.floor, r.room_num, " +
                "       b.building_name, rg.gu_name, rg.dong_name, " +
                "       ABS(r.deposit - ((up.deposit_min + up.deposit_max) / 2)) AS deposit_diff, " +
                "       ABS(r.monthly_rent - ((up.rent_min + up.rent_max) / 2)) AS rent_diff, " +
                "       ABS(r.deposit - ((up.deposit_min + up.deposit_max) / 2)) + " +
                "       ABS(r.monthly_rent - ((up.rent_min + up.rent_max) / 2)) AS total_diff " +
                "FROM user_preference up " +
                "JOIN regions rg ON up.region_id = rg.region_id " +
                "JOIN buildings b ON b.region_id = rg.region_id " +
                "JOIN rooms r ON r.building_id = b.building_id " +
                "WHERE up.user_id = ? " +
                "ORDER BY total_diff ASC " +
                "LIMIT 1"
            );

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("🏠 추천 매물:");
                System.out.println(" - 건물 이름: " + rs.getString("building_name"));
                System.out.println(" - 위치: " + rs.getString("gu_name") + " " + rs.getString("dong_name"));
                System.out.println(" - 방 번호: " + rs.getInt("floor") + "층 " + rs.getInt("room_num") + "호");
                System.out.println(" - 보증금: " + rs.getInt("deposit") + "만원");
                System.out.println(" - 월세: " + rs.getInt("monthly_rent") + "만원");
            } else {
                System.out.println("❌ 조건에 맞는 매물이 없습니다.");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("DB 오류:");
            e.printStackTrace();
        }
    }
}
