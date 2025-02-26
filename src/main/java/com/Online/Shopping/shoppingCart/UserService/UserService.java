package com.Online.Shopping.shoppingCart.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.Online.Shopping.shoppingCart.Entity.Users;
import com.Online.Shopping.shoppingCart.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	public void register(Users users) {
		users.setPassword(encoder.encode(users.getPassword()));
		String role=users.getEmail().endsWith("comapny.com")?"ROLE_USER":"ROLE_ADMIN";
		users.setRole(role);
		repository.save(users);
		
	}

}
