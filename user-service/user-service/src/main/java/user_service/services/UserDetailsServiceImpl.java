package user_service.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import user_service.CustomUserDetails;
import user_service.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		user_service.entities.User user = userRepository.findByUserEmail(username);
		if (Objects.isNull(user)) {

			// Approach 1 ( Limited Access of user data while doing Authenticate for a user)
//			UserDetails userDetails = User.builder()
//			.username(user.getUserName())
//			.password(user.getUserPassword())
//			.roles(user.getUserRole().toArray(new String[0]))
//			.build();
//
//			return userDetails;

			System.out.println("User not found!");
			throw new UsernameNotFoundException("User not found");

		}

		return new CustomUserDetails(user);
	}

}
