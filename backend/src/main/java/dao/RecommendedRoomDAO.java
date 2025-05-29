package main.java.dao;

import main.java.dto.RoomDTO;
import util.DBUtil;

import java.sql.*;

public class RecommendedRoomDAO {

    public RoomDTO getRecommendedRoom(int userId) {
        String query =
            "SELECT " +
            "    r.room_id, r.deposit, r.monthly_rent, r.floor, r.room_num, " +
            "    b.building_name, rg.gu_name, rg.dong_name " +
            "FROM ( " +
            "    SELECT * FROM user_preference WHERE user_id = ? " +
            ") prefs " +
            "JOIN regions rg ON prefs.region_id = rg.region_id " +
            "JOIN buildings b ON b.region_id = rg.region_id " +
            "JOIN rooms r ON r.building_id = b.building_id " +
            "ORDER BY " +
            "    ABS(r.deposit - ((prefs.deposit_min + prefs.deposit_max) / 2)) + " +
            "    ABS(r.monthly_rent - ((prefs.rent_min + prefs.rent_max) / 2)) ASC " +
            "LIMIT 1";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	return new RoomDTO(
            		    rs.getInt("room_id"),
            		    rs.getString("building_name"),
            		    rs.getString("gu_name"),
            		    rs.getString("dong_name"),
            		    rs.getInt("room_num"),
            		    rs.getInt("floor"),
            		    rs.getInt("deposit"),
            		    rs.getInt("monthly_rent")
            		);

            }

        } catch (SQLException e) {
            System.err.println("DB 오류:");
            e.printStackTrace();
        }

        return null;
    }
}
