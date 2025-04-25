package com.tshirtmart.trilo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tshirtmart.trilo.Entities.LoginRequest;
import com.tshirtmart.trilo.Entities.User;

@Service
public interface UserService {
	
	public User addUser(User user);
	
	
	public User getUser(long userId);
	
	public boolean getUserByEmailAndPassword(LoginRequest loginRequest);
	
	
	public List<User> getAllUser();
	
	
	public boolean deleteUser(long userId);
	
	
	public User updateUser(long userId, User user);
	

}
