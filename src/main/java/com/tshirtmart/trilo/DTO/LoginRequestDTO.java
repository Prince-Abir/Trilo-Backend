package com.tshirtmart.trilo.DTO;

 public class LoginRequestDTO {
	 
    private String userEmail;
    private String userPassword;

    public LoginRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginRequestDTO(String userEmail, String userPassword) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

	@Override
	public String toString() {
		return "LoginRequest [userEmail=" + userEmail + ", userPassword=" + userPassword + "]";
	}
    
    
}