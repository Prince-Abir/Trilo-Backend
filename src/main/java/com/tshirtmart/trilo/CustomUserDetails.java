package com.tshirtmart.trilo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tshirtmart.trilo.entities.User;

public class CustomUserDetails implements UserDetails {


	private static final long serialVersionUID = 1L;

	private final User user;


	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> userRole = user.getUserRole();
		List<GrantedAuthority> authorities = new ArrayList<>();

		userRole.forEach(role ->{
			authorities.add(new SimpleGrantedAuthority(role));
		});

		return authorities;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getUserPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
