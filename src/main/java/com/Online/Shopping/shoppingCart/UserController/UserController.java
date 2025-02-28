package com.Online.Shopping.shoppingCart.UserController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Online.Shopping.shoppingCart.Entity.Users;
import com.Online.Shopping.shoppingCart.JWT.JWTUtil;
import com.Online.Shopping.shoppingCart.UserService.UserService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserDetailsService detailsService;
	@Autowired
	private AuthenticationManager manager;
	@PostMapping("/register")
	public String register(@RequestBody Users users) 
	{
		service.register(users);
		return "registered successfully";
	}
	
	@PostMapping("/login")
	public void login(@RequestBody Users  users,HttpServletResponse response)
	{
		Authentication authentication=manager.authenticate(new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword()));
		UserDetails details=detailsService.loadUserByUsername(users.getEmail());
		String token=jwtUtil.genereteToken(details.getUsername());
		ResponseCookie cookie=ResponseCookie.from("jwt", token).httpOnly(true).secure(true).path("/").maxAge(1000*60*60).build();
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
		System.out.println(token +"token genereated");
		
	}
}
