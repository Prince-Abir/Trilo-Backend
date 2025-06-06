package user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import user_service.dto.LoginRequestDTO;
import user_service.dto.UserDTO;
import user_service.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/trilo")
@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class RegAndLogController {


	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping(path = "/register")
	public UserDTO Registration(@RequestBody @Valid UserDTO userDTO) {

		UserDTO savedDTO = userServiceImpl.addUser(userDTO);
		System.out.print(savedDTO);
		return savedDTO;

	}

	@PostMapping(path = "/login")
	public String Login(@RequestBody LoginRequestDTO loginRequestDTO) {

		String status = userServiceImpl.findByUserEmail(loginRequestDTO);
		System.out.println(loginRequestDTO);
		return status;

	}

}
