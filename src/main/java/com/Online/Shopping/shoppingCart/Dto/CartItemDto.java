package com.Online.Shopping.shoppingCart.Dto;

import java.math.BigDecimal;

import com.Online.Shopping.shoppingCart.Entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDto {
	
	private long id;
	private int quantity;
	private long productId;
	private String productName;
	private String productImage;
	private BigDecimal productPrice;

}
