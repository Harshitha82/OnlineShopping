package com.Online.Shopping.shoppingCart.Dto;

import java.math.BigDecimal;
import java.util.List;

import com.Online.Shopping.shoppingCart.Entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

	private long id;
	private BigDecimal price;
	private String name;
	private String image;
	private String description;
}
