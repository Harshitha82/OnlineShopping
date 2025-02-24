package com.Online.Shopping.shoppingCart.Dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.Online.Shopping.shoppingCart.Entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
	private int id;
	private String address;
	private int phnumber;
	private String userName;
	private String Orderstatus;	
	private BigDecimal totalPrice;
	private List<OrderItemDto> items=new ArrayList<>();

}
