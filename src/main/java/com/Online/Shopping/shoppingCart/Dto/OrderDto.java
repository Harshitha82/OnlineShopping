package com.Online.Shopping.shoppingCart.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.Online.Shopping.shoppingCart.Entity.OrderItem;
import com.Online.Shopping.shoppingCart.Entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
	private int id;
	private String address;
	private int phnumber;
	private OrderStatus Orderstatus;
	private LocalDateTime placedAt;
	private BigDecimal totalPrice;
	private List<OrderItemDto> items=new ArrayList<>();

}
