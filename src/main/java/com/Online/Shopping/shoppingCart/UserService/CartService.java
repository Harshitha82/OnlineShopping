package com.Online.Shopping.shoppingCart.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Online.Shopping.shoppingCart.Dto.CartDto;
import com.Online.Shopping.shoppingCart.Dto.CartItemDto;
import com.Online.Shopping.shoppingCart.Entity.Cart;
import com.Online.Shopping.shoppingCart.Entity.CartItem;
import com.Online.Shopping.shoppingCart.Entity.Product;
import com.Online.Shopping.shoppingCart.Entity.Users;
import com.Online.Shopping.shoppingCart.Mapper.CartItemMapper;
import com.Online.Shopping.shoppingCart.Mapper.CartMapper;
import com.Online.Shopping.shoppingCart.Repository.CartRepository;
import com.Online.Shopping.shoppingCart.Repository.CategoryRepository;
import com.Online.Shopping.shoppingCart.Repository.ProductRepository;
import com.Online.Shopping.shoppingCart.Repository.UserRepository;

import jakarta.mail.FetchProfile.Item;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	public CartDto addToCart(String email,long productId,int quantity)
	{
		Users users=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
		Cart cart=cartRepository.findByUserId(users.getId()).orElse(new Cart(users));
		Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("product not found"));
		CartItem cartItem=cart.getCartItems().stream().filter(item->
		item.getProduct().getId()==productId).findFirst().orElse(null);
		if(cartItem!=null)
		{	
			cartItem.setQuantity(cartItem.getQuantity()+1);
		}else {
			cartItem=new CartItem(cart,product);
			cartItem.setQuantity(quantity);
			cart.getCartItems().add(cartItem);
		}
		
		cartRepository.save(cart);
		System.out.println();
		return CartMapper.toDto(cart) ;
	}
	
	public CartDto getAllCartItems(String email)
	{
		Users users=userRepository.findByEmail(email).get();
		Cart cart=cartRepository.findByUserId(users.getId()).orElseThrow();
		return CartMapper.toDto(cart);
	}
	
	public String deleteAllitems(String email) {
		Users users=userRepository.findByEmail(email).get();
		Cart cart=cartRepository.findByUserId(users.getId()).orElseThrow();
		cart.getCartItems().clear();
		cartRepository.save(cart);
		return "removed all items";
	}
	
	public CartDto deleteItem(String email,long productId)
	{
		Users users=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
		Cart cart=cartRepository.findByUserId(users.getId()).orElseThrow();
		cart.getCartItems().removeIf(item->item.getProduct().getId()==productId);
		cartRepository.save(cart);
		return CartMapper.toDto(cart);	
	}
}
