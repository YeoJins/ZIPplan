package main.java.util;

import main.java.dao.AnalysisDAO;
import main.java.dao.UserDAO;
import main.java.dto.UserDTO;
import main.java.dao.UserPreferenceDAO;
import main.java.dto.RegionAverageDTO;
import main.java.dto.RegionAverageRankDTO;
import main.java.dto.TopBuildingsDTO;
import main.java.dto.UserPreferenceDTO;
import java.util.Scanner;


import java.util.List;
public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        AnalysisDAO dao = new AnalysisDAO();
        UserPreferenceDAO dao2 = new UserPreferenceDAO();
        UserDAO dao3 = new UserDAO();


        // System.out.println("== 구별 월세 평균 ==");
        // List<RegionAverageDTO> averages = dao.getRegionAverageList();
        // for (RegionAverageDTO dto : averages) {
        //     System.out.println(dto.getGuName() + ": " + dto.getAverageRent() + "만원");
        // }

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
        
        System.out.println("1. 회원가입\n2. 로그인");
        int choice = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        if (choice == 1) {
            System.out.print("아이디: ");
            String loginId = sc.nextLine();
            System.out.print("비밀번호: ");
            String password = sc.nextLine();
            System.out.print("이메일: ");
            String email = sc.nextLine();
            System.out.print("이름: ");
            String name = sc.nextLine();

            UserDTO user = new UserDTO(0, loginId, password, email, name);
            boolean result = dao3.registerUser(user);
            System.out.println(result ? "회원가입 성공" : "회원가입 실패");
        } else if (choice == 2) {
            System.out.print("아이디: ");
            String loginId = sc.nextLine();
            System.out.print("비밀번호: ");
            String password = sc.nextLine();

            boolean result = dao3.loginUser(loginId, password);
            System.out.println(result ? "로그인 성공" : "로그인 실패");
        } else {
            System.out.println("잘못된 선택입니다.");
        }

        sc.close();

        
    }
}
