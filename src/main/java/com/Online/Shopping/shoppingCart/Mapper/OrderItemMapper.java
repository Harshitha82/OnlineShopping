package com.Online.Shopping.shoppingCart.Mapper;

import com.Online.Shopping.shoppingCart.Dto.OrderItemDto;
import com.Online.Shopping.shoppingCart.Dto.ProductDto;
import com.Online.Shopping.shoppingCart.Entity.OrderItem;

public class OrderItemMapper {

	public static OrderItemDto toDto(OrderItem item)
	{
		ProductDto order=ProductMapper.toDto(item.getProduct());
		return new OrderItemDto(item.getId(),item.getPrice(),item.getQuantity(),order);
	}
}
