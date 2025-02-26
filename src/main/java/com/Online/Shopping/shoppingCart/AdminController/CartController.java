package com.Online.Shopping.shoppingCart.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Online.Shopping.shoppingCart.Dto.CartDto;
import com.Online.Shopping.shoppingCart.Entity.Users;
import com.Online.Shopping.shoppingCart.UserService.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/addtoCart")
	public CartDto addToCart(@AuthenticationPrincipal UserDetails details,@RequestParam long productId,@RequestParam int quantity ) {
		String userEmail=details.getUsername();
		return cartService.addToCart(userEmail, productId, quantity); 
	}
	
	@GetMapping("/cartItems")
	public CartDto findAll(@AuthenticationPrincipal UserDetails details) {
		return cartService.getAllCartItems(details.getUsername());
	}
	@DeleteMapping("/clearCart")
	public String clearCart(@AuthenticationPrincipal UserDetails details)
	{
		return cartService.deleteAllitems(details.getUsername());
	}
	
	@DeleteMapping("/deleteCartItem")
	public CartDto deleteProduct(@AuthenticationPrincipal UserDetails details,@RequestParam long productId)
	{
		return cartService.deleteItem(details.getUsername(), productId);
	}
	
	
	
	
}
