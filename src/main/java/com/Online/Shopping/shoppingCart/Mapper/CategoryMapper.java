package com.Online.Shopping.shoppingCart.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.Online.Shopping.shoppingCart.Dto.CategoryDto;
import com.Online.Shopping.shoppingCart.Dto.ProductDto;
import com.Online.Shopping.shoppingCart.Entity.Category;

public class CategoryMapper {
	
	public static CategoryDto toDto(Category category)
	{
		List<ProductDto> products=category.getProducts().stream()
				.map(ProductMapper::toDto).collect(Collectors.toList());
		return new CategoryDto(category.getId(),
				category.getName(),
				category.getImage(),products);
	}
	public static Category toEntity(CategoryDto dto)
	{
		Category category=new Category();
		category.setId(dto.getId());
		category.setName(dto.getName());
		category.setImage(dto.getImage());
		return category;
	}

}
