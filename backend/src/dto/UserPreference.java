package dto;

import java.sql.Timestamp;

public class UserPreference {
    private int prefId;
    private int userId;
    private int depositMin;
    private int depositMax;
    private int rentMin;
    private int rentMax;
    private int regionId;
    private Timestamp createdAt;

    // 기본 생성자
    public UserPreference() {}

    // getter & setter
    public int getPrefId() { return prefId; }
    public void setPrefId(int prefId) { this.prefId = prefId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getDepositMin() { return depositMin; }
    public void setDepositMin(int depositMin) { this.depositMin = depositMin; }

    public int getDepositMax() { return depositMax; }
    public void setDepositMax(int depositMax) { this.depositMax = depositMax; }

    public int getRentMin() { return rentMin; }
    public void setRentMin(int rentMin) { this.rentMin = rentMin; }

    public int getRentMax() { return rentMax; }
    public void setRentMax(int rentMax) { this.rentMax = rentMax; }

    public int getRegionId() { return regionId; }
    public void setRegionId(int regionId) { this.regionId = regionId; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
