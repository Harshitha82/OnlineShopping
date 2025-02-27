package com.Online.Shopping.shoppingCart.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Online.Shopping.shoppingCart.Dto.OrderDto;
import com.Online.Shopping.shoppingCart.UserService.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@PostMapping("/createOrder")
	@PreAuthorize("hasRole('USER')")
	public OrderDto createOrder(@AuthenticationPrincipal UserDetails details,@RequestParam String address,@RequestParam int phno)
	{
		return orderService.createOrder(details.getUsername(), address, phno);
	}
	@GetMapping("/getUserOrders")
	@PreAuthorize("hasRole('USER')")
	public List<OrderDto> getAllOrdersOfUser(@AuthenticationPrincipal UserDetails details)
	{
		return orderService.getAllByUser(details.getUsername());
	}
	@GetMapping("/getAllOrders")
	@PreAuthorize("hasRole('ADMIN')")
	public List<OrderDto> getAllOrders(@AuthenticationPrincipal UserDetails details)
	{
		return orderService.getAll();
	}
	

}
