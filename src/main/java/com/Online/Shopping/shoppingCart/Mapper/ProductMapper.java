package com.Online.Shopping.shoppingCart.Mapper;

import com.Online.Shopping.shoppingCart.Dto.ProductDto;
import com.Online.Shopping.shoppingCart.Entity.Product;

public class ProductMapper {

	public static ProductDto toDto(Product product)
	{
		return new ProductDto(
				product.getId(),
				product.getPrice(),
				product.getDescription(),
				product.getImage(),product.getName());		
	}
	
	public static Product toEntity(ProductDto dto)
	{
		Product product=new Product();
		product.setId(dto.getId());
		product.setDescription(dto.getDescription());
		product.setImage(dto.getImage());
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		return product;
	}
}
