package com.Online.Shopping.shoppingCart.Mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.Online.Shopping.shoppingCart.Dto.CartItemDto;
import com.Online.Shopping.shoppingCart.Dto.ProductDto;
import com.Online.Shopping.shoppingCart.Entity.CartItem;
import com.Online.Shopping.shoppingCart.Entity.Product;
import com.Online.Shopping.shoppingCart.Repository.ProductRepository;

public class CartItemMapper {
	@Autowired
	
	public static CartItemDto itemDto(CartItem cart)
	{
		return new CartItemDto(
				cart.getId(),
				cart.getQuantity(),ProductMapper.toDto(cart.getProduct()));
	}
	
	public static CartItem toEntity(CartItemDto itemDto)
	{
		CartItem cartItem=new CartItem();
		cartItem.setId(itemDto.getId());
		cartItem.setQuantity(itemDto.getQuantity());
		Product product=ProductMapper.toEntity(itemDto.getProduct());
		cartItem.setProduct(product);
		return cartItem;
	}
	
	

}
