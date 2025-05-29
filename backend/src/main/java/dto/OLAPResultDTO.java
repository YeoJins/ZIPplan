package main.java.dto;

public class OLAPResultDTO {
    private String guName;
    private String buildingName;
    private int avgRent;
    private String level;

    public OLAPResultDTO(String guName, String buildingName, int avgRent, String level) {
        this.guName = guName;
        this.buildingName = buildingName;
        this.avgRent = avgRent;
        this.level = level;
    }

    // Getter 메서드
    public String getGuName() {
        return guName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public int getAvgRent() {
        return avgRent;
    }

    public String getLevel() {
        return level;
    }

    // toString 메서드 (디버깅 및 출력용)
    @Override
    public String toString() {
        return "OLAPResultDTO{" +
                "guName='" + guName + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", avgRent=" + avgRent +
                ", level='" + level + '\'' +
                '}';
    }
}
