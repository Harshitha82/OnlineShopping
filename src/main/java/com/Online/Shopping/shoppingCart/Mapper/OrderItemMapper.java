package com.Online.Shopping.shoppingCart.Mapper;

import java.util.List;

import com.Online.Shopping.shoppingCart.Dto.OrderItemDto;
import com.Online.Shopping.shoppingCart.Dto.ProductDto;
import com.Online.Shopping.shoppingCart.Entity.OrderItem;

public class OrderItemMapper {

	public static OrderItemDto toDto(OrderItem item)
	{
		return new OrderItemDto(item.getId(),item.getPrice(),item.getQuantity(),item.getProduct().getName(),item.getProduct().getImage());
	}
}
