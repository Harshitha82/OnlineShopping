package com.Online.Shopping.shoppingCart.Mapper;

import com.Online.Shopping.shoppingCart.Dto.CartItemDto;
import com.Online.Shopping.shoppingCart.Entity.CartItem;

public class CartItemMapper {
	
	public static CartItemDto itemDto(CartItem cart)
	{
		return new CartItemDto(
				cart.getId(),
				cart.getQuantity(),
				cart.getProduct().getId(),
				cart.getProduct().getName(),
				cart.getProduct().getImage(),
				cart.getProduct().getPrice());
	}
	

}
