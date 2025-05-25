package util;

import dao.AnalysisDAO;
import dao.UserPreferenceDAO;
import dto.RegionAverageDTO;
import dto.RegionAverageRankDTO;
import dto.TopBuildingsDTO;
import dto.UserPreferenceDTO;

import java.util.List;
public class Main {
    public static void main(String[] args) {
        AnalysisDAO dao = new AnalysisDAO();
        UserPreferenceDAO dao2 = new UserPreferenceDAO();

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
        

        // INSERT 테스트
        UserPreferenceDTO pref = new UserPreferenceDTO();
        pref.setUserId(1);           // 실제 DB에 있는 user_id 사용!
        pref.setDepositMin(100000);
        pref.setDepositMax(1000000);
        pref.setRentMin(50000);
        pref.setRentMax(200000);
        pref.setRegionId(3);         // 실제 존재하는 region_id

        dao2.insert(pref);  // ✅ 삽입

        // UPDATE 테스트
        pref.setDepositMin(200000);
        pref.setRentMax(250000);
        dao2.update(pref);  // ✅ 수정

        // DELETE 테스트
        dao2.delete(1); // 1번 유저의 선호 삭제 (user_id = 1 기준)
        
        
        
    }
}
