package com.Online.Shopping.shoppingCart.AdminController;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.Online.Shopping.shoppingCart.Entity.Category;
import com.Online.Shopping.shoppingCart.UserService.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:5174/")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/addCategory")
	public CategoryDto createCategory(@RequestParam String name,@RequestPart MultipartFile file) throws IOException
	{
		return categoryService.createCategory(name, file);
	}
	@DeleteMapping("/deleteCategory")
	public String deleteCategory(@RequestParam String name)
	{
		return categoryService.deleteCategory(name);
	}
	
	@PutMapping("/updateCategory/{id}")
	public CategoryDto updateCategory(@PathVariable long id,@RequestParam String name,@RequestPart MultipartFile file ) throws IOException
	{
		return categoryService.updateCategory(id,name,file);
	}
	
	@GetMapping("/getCategory")
	public List<CategoryDto> getCategory() {
		return categoryService.getCategory();
	}
	@GetMapping("/getById/{id}")
	public CategoryDto getCategoryById(@PathVariable long id)
	{
		return categoryService.getCategory(id);
	}
}
