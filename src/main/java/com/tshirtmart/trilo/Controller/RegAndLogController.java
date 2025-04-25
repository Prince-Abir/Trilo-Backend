package com.tshirtmart.trilo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tshirtmart.trilo.Entities.LoginRequest;
import com.tshirtmart.trilo.Entities.User;
//import org.springframework.web.bind.annotation.RestController;
import com.tshirtmart.trilo.ServiceImpl.UserServiceImpl;

@RestController
@RequestMapping("/trilo")
public class RegAndLogController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@PostMapping(path  = "/register")
	public User Registration(@RequestBody User user) {
		
		User usr = userServiceImpl.addUser(user);
		return usr;
	
		
	}
	
	@PostMapping(path  = "/login")
	public boolean Login(@RequestBody LoginRequest loginRequest) {
		
		return userServiceImpl.getUserByEmailAndPassword(loginRequest);
		
	}


}
