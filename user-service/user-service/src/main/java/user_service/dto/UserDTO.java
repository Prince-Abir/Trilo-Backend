package user_service.dto;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;

    @NotBlank(message = "username cannot be null")
    private String userName;

    @Email(message = "Invalid Email")
    @NotBlank(message = "email must be filled")
    private String userEmail;

    @NotBlank
    @Length(min = 6, message = "Password length should be at leaset 6")
    private String userPassword;

    @NotEmpty(message = "Please select a userRole")
    private List<String> userRole;

    @Pattern(regexp = "^(?:\\+8801|8801|01)[3-9]\\d{8}$", message = "Invalid Bangladeshi phone number format")
    private String userPhone;

    @NotBlank(message = "user address cannot be empty")
    private String userAddress;

    public UserDTO() {
        super();
    }

    public UserDTO(long userId, String userName, String userEmail, String userPassword, List<String> userRole,
                   String userPhone, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<String> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<String> userRole) {
        this.userRole = userRole;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
                + userPassword + ", userRole=" + userRole + ", userPhone=" + userPhone + ", userAddress=" + userAddress
                + "]";
    }
}
