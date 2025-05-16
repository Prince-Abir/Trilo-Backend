package user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user_service.dto.UserDTO;
import user_service.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/trilo")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping(path = "/users")
	public List<UserDTO> getUsers() {

		return userServiceImpl.getAllUser();
	}

	@GetMapping("/{userId}")
	public UserDTO getUser(@PathVariable long userId) {

		UserDTO user = userServiceImpl.getUser(userId);

		return user;
	}

	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable long userId) {

		String status = userServiceImpl.deleteUser(userId);
		return status;

	}

	@PutMapping("/{userId}")
	public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable long userId) {

		UserDTO user = userServiceImpl.updateUser(userId, userDTO);
		return user;
	}

}
