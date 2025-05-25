package dto;

public class RegionAverageDTO {
    private String guName;
    private double averageRent;

    public RegionAverageDTO(String guName, double averageRent) {
        this.guName = guName;
        this.averageRent = averageRent;
    }

    public String getGuName() {
        return guName;
    }

    public double getAverageRent() {
        return averageRent;
    }
}