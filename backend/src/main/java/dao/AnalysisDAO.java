package main.java.dao;

import main.java.dto.RegionAverageDTO;
import main.java.dto.OLAPResultDTO;
import main.java.dto.RegionAverageRankDTO;
import main.java.dto.TopBuildingsDTO;
import util.DBUtil;

import java.sql.*;
import java.util.*;

public class AnalysisDAO {
	

    private Connection conn;

    public AnalysisDAO() {
        try {
            conn = DBUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public List<RegionAverageDTO> getRegionAverageList() {
//        List<RegionAverageDTO> list = new ArrayList<>();
//        String sql = "SELECT gu_name, ROUND(AVG(rent_price)) as avg_rent " +
//                     "FROM apartment_rent r JOIN regions g ON r.region_id = g.region_id " +
//                     "GROUP BY gu_name "+
//                     "HAVING avg_rent >= 2500";
//        try (Connection conn = DBUtil.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                list.add(new RegionAverageDTO(rs.getString("gu_name"), rs.getDouble("avg_rent")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

    public List<RegionAverageRankDTO> getRegionAverageWithRank() {
        List<RegionAverageRankDTO> list = new ArrayList<>();
        String sql = "SELECT gu_name, avg_rent, RANK() OVER (ORDER BY avg_rent DESC) AS 'rank' " +
                "FROM (SELECT gu_name, ROUND(AVG(rent_price)) as avg_rent " +
                "FROM apartment_rent r JOIN regions g ON r.region_id = g.region_id " +
                "GROUP BY gu_name) t";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new RegionAverageRankDTO(rs.getString("gu_name"),
                                                  rs.getDouble("avg_rent"),
                                                  rs.getInt("rank")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TopBuildingsDTO> getTopBuildings() {
        List<TopBuildingsDTO> list = new ArrayList<>();
        String sql = "SELECT b.building_name, g.gu_name, ROUND(AVG(r.monthly_rent)) AS avg_rent " +
                "FROM rooms r " +
                "JOIN buildings b ON r.building_id = b.building_id " +
                "JOIN regions g ON b.region_id = g.region_id " +
                "GROUP BY r.building_id " +
                "HAVING avg_rent >= 70 " +
                "ORDER BY avg_rent DESC " +
                "LIMIT 5";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new TopBuildingsDTO(
                        rs.getString("building_name"),
                        rs.getString("gu_name"),
                        rs.getInt("avg_rent")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<OLAPResultDTO> getMultiDimensionalRentStats() {
        List<OLAPResultDTO> list = new ArrayList<>();
        String sql =
            "SELECT g.gu_name, b.building_name, ROUND(AVG(r.monthly_rent)) AS avg_rent, '구+건물 평균' AS level " +
            "FROM rooms r " +
            "JOIN buildings b ON r.building_id = b.building_id " +
            "JOIN regions g ON b.region_id = g.region_id " +
            "GROUP BY g.gu_name, b.building_name " +
            "UNION ALL " +
            "SELECT g.gu_name, NULL, ROUND(AVG(r.monthly_rent)) AS avg_rent, '구별 평균' AS level " +
            "FROM rooms r " +
            "JOIN buildings b ON r.building_id = b.building_id " +
            "JOIN regions g ON b.region_id = g.region_id " +
            "GROUP BY g.gu_name " +
            "UNION ALL " +
            "SELECT NULL, NULL, ROUND(AVG(r.monthly_rent)) AS avg_rent, '전체 평균' AS level " +
            "FROM rooms r";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new OLAPResultDTO(
                    rs.getString("gu_name"),
                    rs.getString("building_name"),
                    rs.getInt("avg_rent"),
                    rs.getString("level")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}

