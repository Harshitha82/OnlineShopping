package com.Online.Shopping.shoppingCart.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Online.Shopping.shoppingCart.Entity.Users;
import com.Online.Shopping.shoppingCart.UserService.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/register")
	public String register(@RequestBody Users users) 
	{
		service.register(users);
		return "registered successfully";
	}
	@GetMapping("/home")
	public String home() {
		return "User home";
	}
	
//	public S
}
