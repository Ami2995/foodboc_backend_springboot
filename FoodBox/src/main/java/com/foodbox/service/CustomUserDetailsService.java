package com.foodbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodbox.model.CustomUserDetails;
import com.foodbox.model.User;
import com.foodbox.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findUserByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		CustomUserDetails customUser = new CustomUserDetails(user);
		return customUser;
	}
	
	public User getUser(String username) {
		return userRepo.findUserByEmail(username);
	}
	
}
