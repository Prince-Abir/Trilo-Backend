package com.tshirtmart.trilo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tshirtmart.trilo.Dao.UserRepository;
import com.tshirtmart.trilo.Entities.LoginRequest;
import com.tshirtmart.trilo.Entities.User;
import com.tshirtmart.trilo.Services.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public User getUser(long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found!"));
		return user;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public boolean deleteUser(long userId) {
		userRepository.deleteById(userId);
		return true;
	}

	@Override
	public User updateUser(long userId, User user) {
//		Optional<User> oldUser = userRepository.findById(userId);
//		List<User> users = userRepository.findAll();
//		
//		while(users.iterator().hasNext()) {
//			
//			users.get(users)
//			
//			
//			
//			users.iterator().next();
//			
//			
//		}
		return null;
	}

	@Override
	public boolean getUserByEmailAndPassword(LoginRequest loginRequest) {
		List<User> users = userRepository.findAll();
		User u = users.stream().filter(user ->

		user.getUserEmail().equals(loginRequest.getUserEmail()) && user.getUserPassword().equals(loginRequest.getUserPassword())).findFirst()
				.orElse(null);

		if (u != null) {
			return true;
		}

		return false;
	}

}
