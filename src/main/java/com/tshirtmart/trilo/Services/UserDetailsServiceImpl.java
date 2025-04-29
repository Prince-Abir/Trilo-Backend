package com.tshirtmart.trilo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tshirtmart.trilo.Repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		com.tshirtmart.trilo.Entities.User user = userRepository.findByUsername(userName);
		
		
		if(user != null) {
			UserDetails userDetails = User.builder()
			.username(user.getUserName())
			.password(user.getUserPassword())
			.roles(user.getUserRole().toArray(new String[0]))
			.build();
			
			return userDetails;
		}
		
		return null;
	}

}
