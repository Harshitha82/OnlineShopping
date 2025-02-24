package com.Online.Shopping.shoppingCart.UserService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Online.Shopping.shoppingCart.Dto.CategoryDto;
import com.Online.Shopping.shoppingCart.Dto.ProductDto;
import com.Online.Shopping.shoppingCart.Entity.Category;
import com.Online.Shopping.shoppingCart.Entity.Product;
import com.Online.Shopping.shoppingCart.Mapper.ProductMapper;
import com.Online.Shopping.shoppingCart.Repository.CategoryRepository;
import com.Online.Shopping.shoppingCart.Repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;

	private static final String UPLOAD_DRI=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\productImages\\";

	public ProductDto addProduct(String name, MultipartFile image, String desscription, String categoryName, BigDecimal price) throws IOException {

		Product product=new Product();
		Optional<Category> category=categoryRepository.findByName(categoryName);
		
		if(category.isPresent())
		{
			product.setCategory(category.get());
		}else {
			throw new UsernameNotFoundException("Category not found");
		}
		Path paths=Paths.get(UPLOAD_DRI,image.getOriginalFilename());
		Files.createDirectories(paths.getParent());
		Files.write(paths,image.getBytes());
		
		product.setDescription(desscription);
		product.setImage("productImages/"+image.getOriginalFilename());
		product.setName(name);
		product.setPrice(price);
		repository.save(product);
		return ProductMapper.toDto(product);
	}
	
	public ProductDto getProductById(long id)
	{
		Optional<Product> product=repository.findById(id);
		if(product.isPresent()) {
			return ProductMapper.toDto(product.get());
		}else {
			throw new UsernameNotFoundException("Product not found");
		}
	}
	
	public List<ProductDto> getAllProducts()
	{
		List<Product> product= repository.findAll();
		return product.stream().map(ProductMapper::toDto).collect(Collectors.toList());
	}
	
	public ProductDto updateProduct(long id,String categoryName,String description,MultipartFile image,String name,BigDecimal price) throws IOException
	{
		Product product=repository.findById(id).get();
		Optional<Category> category=categoryRepository.findByName(categoryName);
		if(category.isPresent())
		{
			product.setCategory(category.get());
		}
		else {
			throw new UsernameNotFoundException("Category not found");
		}
		product.setDescription(description);
		Path paths=Paths.get(UPLOAD_DRI,image.getOriginalFilename());
		Files.write(paths,image.getBytes());
		product.setImage("productImages/"+image.getOriginalFilename());
		product.setName(name);
		product.setPrice(price);
		repository.save(product);
		return ProductMapper.toDto(product);
	}
	
	public String deleteProductByName(String name)
	{
		Product product=repository.findByName(name);
		if(product==null)
		{
			throw new UsernameNotFoundException("Category not found");
		}
		repository.delete(product);
		return "Successfuly deleted product"+name;
	}
	
	public String deleteProductById(long id)
	{
		Optional<Product> product=repository.findById(id);
		if(product.isPresent())
		{
			repository.deleteById(id);
			return "succesfuly deleted product";
		}else {
			throw new UsernameNotFoundException("Category not found");
		}
		
	}
	
}
