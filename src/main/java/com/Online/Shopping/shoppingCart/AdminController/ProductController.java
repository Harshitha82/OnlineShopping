package com.Online.Shopping.shoppingCart.AdminController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Online.Shopping.shoppingCart.Dto.CategoryDto;
import com.Online.Shopping.shoppingCart.Dto.ProductDto;
import com.Online.Shopping.shoppingCart.Entity.Category;
import com.Online.Shopping.shoppingCart.Entity.Product;
import com.Online.Shopping.shoppingCart.UserService.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping("/addProduct")
	public ProductDto addProduct(@RequestParam String name,@RequestParam BigDecimal price,@RequestPart MultipartFile image,@RequestParam String desscription,@RequestParam String categoryName) throws IOException
	{
		return service.addProduct(name,image,desscription,categoryName,price);
	}
	
	@GetMapping("/getById/{id}")
	public ProductDto getProductById(@PathVariable long id)
	{
		return service.getProductById(id);
	}
	
	@GetMapping("/getAllProduct")
	public List<ProductDto> getAllProduct()
	{
		return service.getAllProducts();
	}
	@PutMapping("/updateProduct/{id}")
	public ProductDto updateProduct(@PathVariable long id,@RequestParam String categoryName,@RequestParam String description,@RequestPart MultipartFile image,@RequestParam String name,@RequestParam BigDecimal price) throws IOException
	{
		return service.updateProduct(id, categoryName, description, image, name, price);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String deleteProduct(@PathVariable long id)
	{
		return service.deleteProductById(id);
	}

}
