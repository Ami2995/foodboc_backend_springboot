package com.foodbox.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.model.Role;
import com.foodbox.model.User;
import com.foodbox.repository.RoleRepository;
import com.foodbox.repository.UserRepository;
import com.foodbox.service.CustomUserDetailsService;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/user")
public class UserContoller {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
    Logger log = Logger.getAnonymousLogger();
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		User u = userRepo.findUserByEmail(user.getEmailAddress());
		if(u!=null) {
			log.info("User is already there !!");
			throw new Exception("User already present !!");
		}else {
			String password = user.getPassword();
			user.setPassword(bcrypt.encode(password));
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepo.findById(2l).get());
			user.setRoles(roles);
			return userRepo.save(user);
		}
		
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable String username) {
		return  userService.getUser(username);
	}
}
