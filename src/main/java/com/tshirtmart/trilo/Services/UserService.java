package com.tshirtmart.trilo.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tshirtmart.trilo.DTO.LoginRequestDTO;
import com.tshirtmart.trilo.DTO.UserDTO;

@Service
public interface UserService {

	public UserDTO addUser(UserDTO userDTO);


	public UserDTO getUser(long userId);


	public String findByUserEmail(LoginRequestDTO loginRequestDTO);


	public List<UserDTO> getAllUser();


	public String deleteUser(long userId);


	public UserDTO updateUser(long userId, UserDTO userDTO);



}
