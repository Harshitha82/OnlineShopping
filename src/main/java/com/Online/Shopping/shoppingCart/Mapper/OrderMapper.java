package com.Online.Shopping.shoppingCart.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.Online.Shopping.shoppingCart.Dto.OrderDto;
import com.Online.Shopping.shoppingCart.Dto.OrderItemDto;
import com.Online.Shopping.shoppingCart.Entity.Order;

public class OrderMapper {
	
	public static OrderDto dto(Order order)
	{
		List<OrderItemDto> orderItems=order.getItems().
				stream().map(OrderItemMapper::toDto).collect(Collectors.toList());
		return new OrderDto(order.getId(),
				order.getAddress(),
				order.getPhnumber(),
				order.getUser().getName(),
				order.getOrderstatus(),
				order.getTotalPrice(),orderItems
				);
	}

}
