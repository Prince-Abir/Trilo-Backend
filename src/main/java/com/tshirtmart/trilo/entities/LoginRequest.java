package com.tshirtmart.trilo.entities;

 public class LoginRequest {

    private String userEmail;
    private String userPassword;

    public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginRequest(String userEmail, String userPassword) {
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