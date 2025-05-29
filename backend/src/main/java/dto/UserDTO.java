package main.java.dto;

public class UserDTO {
	private int userId;  
    private String loginId;
    private String password;
    private String email;
    private String userName;

    public UserDTO(int userId, String loginId, String password, String email, String userName) {
        this.userId = userId;
    	this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.userName = userName;
    }
    
    public int getUserId() {
        return userId;
    }


    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
