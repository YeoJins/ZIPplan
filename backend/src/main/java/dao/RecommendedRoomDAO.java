package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.dto.RoomDTO;
import util.DBUtil;

public class RecommendedRoomDAO {

	public RoomDTO getRecommendedRoom(int userId) {
	    String query =
	        "SELECT " +
	        "    room_id, deposit, monthly_rent, floor, room_num, " +
	        "    building_name, gu_name, dong_name " +
	        "FROM ( " +
	        "    SELECT *, " +
	        "        ABS(((deposit_min + deposit_max) / 2) - deposit) + " +
	        "        ABS(((rent_min + rent_max) / 2) - monthly_rent) AS total_diff " +
	        "    FROM matched_rooms_view " +
	        "    WHERE user_id = ? " +
	        ") AS scored_rooms " +
	        "ORDER BY total_diff " +
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
