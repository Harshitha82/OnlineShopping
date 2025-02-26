package com.Online.Shopping.shoppingCart.Mapper;

import java.math.BigDecimal;
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
		System.out.println(getcart);
		System.out.println(getcart.size());
		BigDecimal totalPrice=getcart.stream().map(item->
		item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal::add);
		return new CartDto(cart.getId(),getcart,totalPrice);
	}

}
