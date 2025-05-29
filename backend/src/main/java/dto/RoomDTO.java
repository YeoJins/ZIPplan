package main.java.dto;

public class RoomDTO {
    private int roomId;
    private String buildingName;
    private String guName;
    private String dongName;
    private int roomNum;
    private int floor;
    private int deposit;
    private int monthlyRent;

    // 생성자
    public RoomDTO(int roomId, String buildingName, String guName, String dongName, int roomNum, int floor, int deposit, int monthlyRent) {
        this.roomId = roomId;
        this.buildingName = buildingName;
        this.guName = guName;
        this.dongName = dongName;
        this.roomNum = roomNum;
        this.floor = floor;
        this.deposit = deposit;
        this.monthlyRent = monthlyRent;
    }

    // getter 메서드
    public int getRoomId() { return roomId; }
    public String getBuildingName() { return buildingName; }
    public String getGuName() { return guName; }
    public String getDongName() { return dongName; }
    public int getRoomNum() { return roomNum; }
    public int getFloor() { return floor; }
    public int getDeposit() { return deposit; }
    public int getMonthlyRent() { return monthlyRent; }
}
