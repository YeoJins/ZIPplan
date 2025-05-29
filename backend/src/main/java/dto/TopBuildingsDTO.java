package main.java.dto;

public class TopBuildingsDTO {
    private String buildingName;
    private String guName;
    private int averageRent;

    public TopBuildingsDTO(String buildingName, String guName, int averageRent) {
        this.buildingName = buildingName;
        this.guName = guName;
        this.averageRent = averageRent;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getGuName() {
        return guName;
    }

    public int getAverageRent() {
        return averageRent;
    }
}
