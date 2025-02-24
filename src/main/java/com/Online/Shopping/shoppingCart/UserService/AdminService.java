package com.Online.Shopping.shoppingCart.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Online.Shopping.shoppingCart.Entity.Admin;
import com.Online.Shopping.shoppingCart.Entity.Users;
import com.Online.Shopping.shoppingCart.Repository.AdminRepository;
import com.Online.Shopping.shoppingCart.Repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository repository;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	public void register(Admin admin) {
		admin.setPassword(encoder.encode(admin.getPassword()));
		repository.save(admin);
		System.out.println(admin.getPassword());
		
	}


}
