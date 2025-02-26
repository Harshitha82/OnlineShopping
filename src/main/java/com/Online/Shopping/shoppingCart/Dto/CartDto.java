package com.Online.Shopping.shoppingCart.Dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.Online.Shopping.shoppingCart.Entity.CartItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CartDto {

	private long id;
	private List<CartItemDto> cartItem=new ArrayList<>();
	private BigDecimal totalPrice;
}
