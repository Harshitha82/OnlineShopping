package com.Online.Shopping.shoppingCart.Dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.Online.Shopping.shoppingCart.Entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDto {

	private long id;
	private BigDecimal price;
	private int quantity;
	private ProductDto product;



}
