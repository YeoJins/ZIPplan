// UserDAO.java
package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.dto.UserDTO;
import main.java.util.DBUtil;

public class UserDAO {
    private Connection conn;

    public UserDAO() {
        conn = DBUtil.getConnection();
    }

    public boolean registerUser(UserDTO user) {
        String sql = "INSERT INTO users (login_id, password, email, user_name) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getLoginId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserName());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginUser(String loginId, String password) {
        String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, loginId);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // true if match found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public UserDTO getUserByCredentials(String loginId, String password) {
        String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, loginId);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new UserDTO(
                    rs.getInt("user_id"),
                    rs.getString("login_id"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("user_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}