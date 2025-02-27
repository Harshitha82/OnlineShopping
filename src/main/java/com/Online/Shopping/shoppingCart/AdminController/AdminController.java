package com.Online.Shopping.shoppingCart.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Online.Shopping.shoppingCart.Entity.Admin;
import com.Online.Shopping.shoppingCart.Entity.Users;
import com.Online.Shopping.shoppingCart.UserService.AdminService;
import com.Online.Shopping.shoppingCart.UserService.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/home")
	@PreAuthorize("hasRole('ADMIN')")
	public String home() {
		return "Admin home";
	}
	
	
	
}
