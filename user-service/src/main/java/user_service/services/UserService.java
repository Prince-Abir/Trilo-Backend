package user_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import user_service.dto.LoginRequestDTO;
import user_service.dto.UserDTO;

@Service
public interface UserService {

	public UserDTO addUser(UserDTO userDTO);


	public UserDTO getUser(long userId);


	public String findByUserEmail(LoginRequestDTO loginRequestDTO);


	public List<UserDTO> getAllUser();


	public String deleteUser(long userId);


	public UserDTO updateUser(long userId, UserDTO userDTO);



}
