package com.tshirtmart.trilo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tshirtmart.trilo.DTO.LoginRequestDTO;
import com.tshirtmart.trilo.DTO.UserDTO;
import com.tshirtmart.trilo.Entities.LoginRequest;
import com.tshirtmart.trilo.Entities.User;
//import org.springframework.web.bind.annotation.RestController;
import com.tshirtmart.trilo.ServiceImpl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trilo")
@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class RegAndLogController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@PostMapping(path  = "/register")
	public UserDTO Registration(@RequestBody @Valid UserDTO userDTO) {
		
		UserDTO savedDTO = userServiceImpl.addUser(userDTO);
		return savedDTO;
	
	}
	
	@PostMapping(path  = "/login")
	public String Login(@RequestBody LoginRequestDTO loginRequestDTO) {
		System.out.println(loginRequestDTO);
		
		return userServiceImpl.findByUserEmail(loginRequestDTO);
		
	}
	
	@GetMapping(path = "/users")
	public List<UserDTO> getUsers(){
		
		return userServiceImpl.getAllUser();
	}


}
