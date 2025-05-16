package payment_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private int userId;
	
	private String userName;
	
	private String userEmail;
	
	private String userPassword;
	
	private String userAddress;
	

}
