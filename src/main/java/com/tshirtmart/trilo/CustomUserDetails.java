package com.tshirtmart.trilo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	
	private static final long serialVersionUID = 1L;
	
	private final com.tshirtmart.trilo.Entities.User user;
	

	public CustomUserDetails(com.tshirtmart.trilo.Entities.User user) {
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
		return user.getUserName();
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
