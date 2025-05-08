package com.tshirtmart.trilo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tshirtmart.trilo.dto.LoginRequestDTO;
import com.tshirtmart.trilo.dto.UserDTO;
import com.tshirtmart.trilo.entities.User;
import com.tshirtmart.trilo.exceptions.UserNotFoundException;
import com.tshirtmart.trilo.repository.UserRepository;
import com.tshirtmart.trilo.services.JwtService;
import com.tshirtmart.trilo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private CacheManager cacheManager;

	public final String KEY = "UserList";

	@Autowired
	public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
	}

	// Convert Entity to DTO
	private UserDTO convertToDTO(User user) {
		List<String> roles = user.getUserRole() != null ? new ArrayList<>(user.getUserRole()) : new ArrayList<>();

		return new UserDTO(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserPassword(), roles,
				user.getUserPhone(), user.getUserAddress());
	}

	// Convert DTO to Entity
	private User convertToEntity(UserDTO userDTO) {
		return new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getUserEmail(), userDTO.getUserPassword(),
				userDTO.getUserRole(), userDTO.getUserPhone(), userDTO.getUserAddress());
	}

	@Override
	public UserDTO addUser(UserDTO userDTO) {

		String pass = bCryptPasswordEncoder.encode(userDTO.getUserPassword());
		userDTO.setUserPassword(pass);

		User savedUser = userRepository.save(convertToEntity(userDTO));
		userDTO.setUserId(savedUser.getUserId());

		// Store in Redis before saving to DB for caching purposes
		redisTemplate.opsForHash().put(KEY, String.valueOf(userDTO.getUserId()), userDTO);
		redisTemplate.opsForValue().set(savedUser.getUserEmail(), String.valueOf(savedUser.getUserId()));



		return userDTO;
	}

	@Override
	public UserDTO getUser(long userId) {
		UserDTO user = (UserDTO) redisTemplate.opsForHash().get(KEY, String.valueOf(userId));

		if (user != null) {
			System.out.print("fethed form Redis");
			return user;
		} else {
			User savedUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));

			UserDTO userDTO = convertToDTO(savedUser);

			// No need for Hibernate.initialize(userDTO.getUserRole()) here

			redisTemplate.opsForHash().put(KEY, String.valueOf(userDTO.getUserId()), userDTO);
			redisTemplate.opsForValue().set(userDTO.getUserEmail(), String.valueOf(userDTO.getUserId()));

			System.out.print("fethed form DB");
			return userDTO;
		}
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<Object> usersInCache = redisTemplate.opsForHash().values(KEY);

		// Check if cache is populated
		if (usersInCache != null && !usersInCache.isEmpty()) {
			System.out.print("fethed form Redis");
			return usersInCache.stream().filter(Objects::nonNull).map(obj -> (UserDTO) obj)
					.collect(Collectors.toList());
		}

		// Fallback to DB if cache is empty
		List<User> users = userRepository.findAll();

		List<UserDTO> userDTOList = new ArrayList<>();
		users.forEach(user -> {
			// Ensure roles are initialized if lazy
			//Hibernate.initialize(user.getUserRole());

			UserDTO userDTO = convertToDTO(user);
			userDTOList.add(userDTO);

			// Store in Redis cache
			redisTemplate.opsForHash().put(KEY, String.valueOf(userDTO.getUserId()), userDTO);
			redisTemplate.opsForValue().set(userDTO.getUserEmail(), String.valueOf(userDTO.getUserId()));
		});

		System.out.print("fethed form DB");

		return userDTOList;
	}

	@Override
	public String deleteUser(long userId) {
		User dbUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not Found with id: " + userId));

		redisTemplate.opsForHash().delete(KEY, String.valueOf(userId));
		userRepository.deleteById(userId);

		return "User: " + userId + " successfully deleted";
	}

	@Override
	public UserDTO updateUser(long userId, UserDTO modifiedUserDTO) {
		User dbUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not Found with id: " + userId));

		String pass = bCryptPasswordEncoder.encode(modifiedUserDTO.getUserPassword());
		modifiedUserDTO.setUserId(dbUser.getUserId());
		modifiedUserDTO.setUserPassword(pass);

		redisTemplate.opsForHash().delete(KEY, String.valueOf(modifiedUserDTO.getUserId()));
		redisTemplate.opsForHash().put(KEY, String.valueOf(modifiedUserDTO.getUserId()), modifiedUserDTO);
		redisTemplate.opsForValue().set(modifiedUserDTO.getUserEmail(), String.valueOf(modifiedUserDTO.getUserId()));



		User savedUser = userRepository.save(convertToEntity(modifiedUserDTO));

		return convertToDTO(savedUser);
	}

	@Override
	public String findByUserEmail(LoginRequestDTO loginRequestDTO) {

		String userId = (String) redisTemplate.opsForValue().get(loginRequestDTO.getUserEmail());
		UserDTO redisUser = (UserDTO) redisTemplate.opsForHash().get(KEY, String.valueOf(userId));

		if (redisUser != null) {
			boolean status = bCryptPasswordEncoder.matches(loginRequestDTO.getUserPassword(),
					redisUser.getUserPassword());

			if (status) {
				return redisUser.getUserName() + ", login successful from Redis";
			} else {
				return "Password does not match";
			}
		}
		User user =  userRepository.findByUserEmail(loginRequestDTO.getUserEmail());
		UserDTO useDTO = convertToDTO(user);


		boolean status = bCryptPasswordEncoder.matches(loginRequestDTO.getUserPassword(), useDTO.getUserPassword());

		if (status) {

			redisTemplate.opsForHash().put(KEY, String.valueOf(useDTO.getUserId()), useDTO);
			redisTemplate.opsForValue().set(useDTO.getUserEmail(), String.valueOf(useDTO.getUserId()));

			return useDTO.getUserName() + ", login successful from DB";
		} else {
			return "Password does not match";
		}

		// Uncommented code (authentication using JWT)
		/*
		 * Authentication authenticate = authenticationManager.authenticate( new
		 * UsernamePasswordAuthenticationToken(loginRequestDTO.getUserEmail(),
		 * loginRequestDTO.getUserPassword()) );
		 *
		 * if (authenticate.isAuthenticated()) { return
		 * jwtService.generateToken(loginRequestDTO); } return "failure";
		 */
	}
}
