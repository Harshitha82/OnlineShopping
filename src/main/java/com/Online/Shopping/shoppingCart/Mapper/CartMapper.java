package com.Online.Shopping.shoppingCart.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.Online.Shopping.shoppingCart.Dto.CartDto;
import com.Online.Shopping.shoppingCart.Dto.CartItemDto;
import com.Online.Shopping.shoppingCart.Dto.OrderDto;
import com.Online.Shopping.shoppingCart.Entity.Cart;
import com.Online.Shopping.shoppingCart.Entity.CartItem;

public class CartMapper {
	
	public static CartDto toDto(Cart cart)
	{
		List<CartItemDto> getcart=cart.getCartItems().stream().
				map(CartItemMapper::itemDto).collect(Collectors.toList());
		BigDecimal totalPrice=getcart.stream().map(item->
		item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal::add);
		return new CartDto(cart.getId(),getcart,totalPrice);
	}
	
	public static Cart toEntity(CartDto cartDto)
	{
		Cart cart=new Cart();
		cart.setId(cartDto.getId());
		List<CartItem> cartDto2=cartDto.getCartItem().stream().map(CartItemMapper::toEntity).collect(Collectors.toList());
		cart.setCartItems(cartDto2);
		return cart;
	}
	



}
