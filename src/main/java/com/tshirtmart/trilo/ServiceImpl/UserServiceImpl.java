package com.tshirtmart.trilo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tshirtmart.trilo.DTO.LoginRequestDTO;
import com.tshirtmart.trilo.DTO.UserDTO;
import com.tshirtmart.trilo.Entities.LoginRequest;
import com.tshirtmart.trilo.Entities.User;
import com.tshirtmart.trilo.Repository.UserRepository;
import com.tshirtmart.trilo.Services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
		super();
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
	}

	// Convert Entity to DTO
	private UserDTO convertToDTO(User user) {
		return new UserDTO(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserPassword(),
				user.getUserRole(), user.getUserPhone(), user.getUserAddress());
	}

	// Convert DTO to Entity
	private User convertToEntity(UserDTO userDTO) {
		return new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getUserEmail(), userDTO.getUserPassword(),
				userDTO.getUserRole(), userDTO.getUserPhone(), userDTO.getUserAddress());
	}

	@Override
	public UserDTO addUser(UserDTO userDTO) {

		User user = convertToEntity(userDTO);
		String pass = bCryptPasswordEncoder.encode(user.getUserPassword());
		user.setUserPassword(pass);
		User savedUser = userRepository.save(user);

		return convertToDTO(savedUser);

	}

	@Override
	public UserDTO getUser(long userId) {
		User savedUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found!"));

		return convertToDTO(savedUser);
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDTO = new ArrayList<>();

		users.forEach(user -> {

			userDTO.add(convertToDTO(user));

		});
		return userDTO;
	}

	@Override
	public boolean deleteUser(long userId) {
		userRepository.deleteById(userId);
		return true;
	}

	@Override
	public UserDTO updateUser(long userId, UserDTO userDTO) {

		return null;
	}

	@Override
	public boolean findByUserEmail(LoginRequestDTO loginRequestDTO) {
		
		User user = userRepository.findByUserEmail(loginRequestDTO.getUserEmail());
		if(Objects.nonNull(user)) {
			return bCryptPasswordEncoder.matches(loginRequestDTO.getUserPassword(), user.getUserPassword());
		}
		return false;
	}


}
