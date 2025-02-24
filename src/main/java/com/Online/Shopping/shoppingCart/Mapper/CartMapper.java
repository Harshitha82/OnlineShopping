package com.Online.Shopping.shoppingCart.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.Online.Shopping.shoppingCart.Dto.CartDto;
import com.Online.Shopping.shoppingCart.Dto.CartItemDto;
import com.Online.Shopping.shoppingCart.Entity.Cart;

public class CartMapper {
	
	public static CartDto toDto(Cart cart)
	{
		List<CartItemDto> getcart=cart.getCartItems().stream().
				map(CartItemMapper::itemDto).collect(Collectors.toList());
		return new CartDto(cart.getId(),getcart,cart.getTotalPrice());
	}

}
