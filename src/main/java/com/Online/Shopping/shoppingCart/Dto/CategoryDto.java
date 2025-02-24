package com.Online.Shopping.shoppingCart.Dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto 
{
	private long id;
	private String name;
	private String image;
	private List<ProductDto> produucts=new ArrayList<>();
}
