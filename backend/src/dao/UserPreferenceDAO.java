package dao;

import dto.UserPreference;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPreferenceDAO {

    // INSERT
    public void insert(UserPreference pref) {
        String sql = "INSERT INTO user_preference (user_id, deposit_min, deposit_max, rent_min, rent_max, region_id, created_at) " +
                     "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pref.getUserId());
            pstmt.setInt(2, pref.getDepositMin());
            pstmt.setInt(3, pref.getDepositMax());
            pstmt.setInt(4, pref.getRentMin());
            pstmt.setInt(5, pref.getRentMax());
            pstmt.setInt(6, pref.getRegionId());

            pstmt.executeUpdate();
            System.out.println("✅ 등록 성공!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void update(UserPreference pref) {
        String sql = "UPDATE user_preference SET deposit_min=?, deposit_max=?, rent_min=?, rent_max=?, region_id=? WHERE pref_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pref.getDepositMin());
            pstmt.setInt(2, pref.getDepositMax());
            pstmt.setInt(3, pref.getRentMin());
            pstmt.setInt(4, pref.getRentMax());
            pstmt.setInt(5, pref.getRegionId());
            pstmt.setInt(6, pref.getPrefId());

            pstmt.executeUpdate();
            System.out.println("✅ 수정 성공!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int prefId) {
        String sql = "DELETE FROM user_preference WHERE pref_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, prefId);
            pstmt.executeUpdate();
            System.out.println("✅ 삭제 성공!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SELECT (전체 조회)
    public List<UserPreference> selectAll() {
        List<UserPreference> list = new ArrayList<>();
        String sql = "SELECT * FROM user_preference";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UserPreference pref = new UserPreference();
                pref.setPrefId(rs.getInt("pref_id"));
                pref.setUserId(rs.getInt("user_id"));
                pref.setDepositMin(rs.getInt("deposit_min"));
                pref.setDepositMax(rs.getInt("deposit_max"));
                pref.setRentMin(rs.getInt("rent_min"));
                pref.setRentMax(rs.getInt("rent_max"));
                pref.setRegionId(rs.getInt("region_id"));
                pref.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(pref);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
