package main.java.dao;

import main.java.dto.UserPreferenceDTO;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserPreferenceDAO {

    public void insert(UserPreferenceDTO pref) {
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
            System.out.println("✅ user_preference INSERT 성공");

        } catch (SQLException e) {
            System.out.println("❌ user_preference INSERT 실패");
            e.printStackTrace();
        }
    }
    
    // 🔁 UPDATE (수정)
    public void update(UserPreferenceDTO pref) {
        String sql = "UPDATE user_preference SET deposit_min = ?, deposit_max = ?, rent_min = ?, rent_max = ?, region_id = ? WHERE user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pref.getDepositMin());
            pstmt.setInt(2, pref.getDepositMax());
            pstmt.setInt(3, pref.getRentMin());
            pstmt.setInt(4, pref.getRentMax());
            pstmt.setInt(5, pref.getRegionId());
            pstmt.setInt(6, pref.getUserId());

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("✅ user_preference UPDATE 성공");
            } else {
                System.out.println("❗ user_id에 해당하는 데이터가 없어 UPDATE 실패");
            }

        } catch (SQLException e) {
            System.out.println("❌ user_preference UPDATE 실패");
            e.printStackTrace();
        }
    }

    // ❌ DELETE (삭제)
    public void delete(int userId) {
        String sql = "DELETE FROM user_preference WHERE user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("🗑️ user_preference DELETE 성공");
            } else {
                System.out.println("❗ user_id에 해당하는 데이터가 없어 DELETE 실패");
            }

        } catch (SQLException e) {
            System.out.println("❌ user_preference DELETE 실패");
            e.printStackTrace();
        }
    }
    
    

}