package com.Online.Shopping.shoppingCart.Dto;

import java.util.ArrayList;
import java.util.List;

import com.Online.Shopping.shoppingCart.Entity.CartItem;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CartDto {

	private long id;
	private List<CartItemDto> cartItem=new ArrayList<>();
	private Double totalPrice;
}
