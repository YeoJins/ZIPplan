package dto;

public class RegionAverageRankDTO {
    private String guName;
    private double averageRent;
    private int rank;

    public RegionAverageRankDTO(String guName, double averageRent, int rank) {
        this.guName = guName;
        this.averageRent = averageRent;
        this.rank = rank;
    }

    public String getGuName() {
        return guName;
    }

    public double getAverageRent() {
        return averageRent;
    }

    public int getRank() {
        return rank;
    }
}
