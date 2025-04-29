package com.tshirtmart.trilo.DTO;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



public class UserDTO {
	
	private long userId;
	
	@NotBlank(message = "username cannot be null")
	private String userName;
	
	
	@Email(message = "Invalid Email")
	@NotBlank(message = "email must be filled")
	private String userEmail;
	
	@NotBlank
	@Length(min = 6,message = "Password length should be at leaset 6")
	private String userPassword;
	
	@NotBlank(message = "Please select a userRole")
	private List<String> userRole;
	
	@Pattern(regexp = "^(?:\\+8801|8801|01)[3-9]\\d{8}$", message = "Invalid Bangladeshi phone number format")
	private String userPhone;
	
	@NotBlank(message = "user address cannot be empty")
	private String userAddress;
	

	public UserDTO() {
		super();
	}


	public UserDTO(long userId, @NotBlank(message = "username cannot be null") String userName,
			@Email(message = "Invalid Email") @NotBlank(message = "email must be filled") String userEmail,
			@NotBlank @Length(min = 6, message = "Password length should be at leaset 6") String userPassword,
			@NotBlank(message = "Please select a userRole") List<String> userRole,
			@Pattern(regexp = "^(?:\\+8801|8801|01)[3-9]\\d{8}$", message = "Invalid Bangladeshi phone number format") String userPhone,
			@NotBlank(message = "user address cannot be empty") String userAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
	}

	public String getUserName() {
		return userName;
	}
	
	public long getUserId() {
		return userId;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public String getUserPassword() {
		return userPassword;
	}



	public String getUserPhone() {
		return userPhone;
	}



	public String getUserAddress() {
		return userAddress;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	public List<String> getUserRole() {
		return userRole;
	}


	public void setUserRole(List<String> userRole) {
		this.userRole = userRole;
	}
	
	
	


}
