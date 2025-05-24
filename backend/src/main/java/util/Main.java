package util;

import dao.AnalysisDAO;
import dto.RegionAverageDTO;
import dto.RegionAverageRankDTO;
import dto.TopBuildingsDTO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnalysisDAO dao = new AnalysisDAO();

        System.out.println("== 구별 월세 평균 ==");
        List<RegionAverageDTO> averages = dao.getRegionAverageList();
        for (RegionAverageDTO dto : averages) {
            System.out.println(dto.getGuName() + ": " + dto.getAverageRent() + "만원");
        }

        System.out.println("\n== 구별 월세 순위 ==");
        List<RegionAverageRankDTO> ranks = dao.getRegionAverageWithRank();
        for (RegionAverageRankDTO dto : ranks) {
            System.out.println(dto.getRank() + ". " + dto.getGuName() + ": " + dto.getAverageRent() + "만원");
        }

        System.out.println("\n== 상품 랭킹 ==");
        List<TopBuildingsDTO> buildings = dao.getTopBuildings();
        for (TopBuildingsDTO dto : buildings) {
            System.out.println(dto.getBuildingName() + " (" + dto.getGuName() + "): " + dto.getAverageRent() + "만원");
        }
    }
}
