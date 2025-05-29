package main.java.dao;

import main.java.dto.RoomDTO;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchedRoomsDAO {

    public List<RoomDTO> getMatchedRooms(int userId) {
        List<RoomDTO> roomList = new ArrayList<>();

        String query = "SELECT * FROM matched_rooms_view WHERE user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RoomDTO room = new RoomDTO(
                        rs.getInt("room_id"),
                        rs.getString("building_name"),
                        rs.getString("gu_name"),
                        rs.getString("dong_name"),
                        rs.getInt("room_num"),
                        rs.getInt("floor"),
                        rs.getInt("deposit"),
                        rs.getInt("monthly_rent")
                );
                roomList.add(room);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("DB 오류:");
            e.printStackTrace();
        }

        return roomList;
    }
}
