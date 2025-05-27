package main;
import dao.UserPreferenceDAO;
import dto.UserPreference;

public class UserPreferenceTest {
    public static void main(String[] args) {
        UserPreferenceDAO dao = new UserPreferenceDAO();

        // INSERT
        UserPreference pref = new UserPreference();
        pref.setUserId(1); // 💡 실제 DB에 있는 user_id 사용해야 함!
        pref.setDepositMin(500000);
        pref.setDepositMax(2000000);
        pref.setRentMin(30000);
        pref.setRentMax(100000);
        pref.setRegionId(3); // 💡 실제 존재하는 region_id

        dao.insert(pref);

        // UPDATE (선택적으로 사용)
        pref.setPrefId(1); // 수정할 pref_id
        pref.setRentMax(150000);
        dao.update(pref);

        // DELETE (선택적으로 사용)
        // dao.delete(1);
    }
}
