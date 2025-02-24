package com.Online.Shopping.shoppingCart.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Online.Shopping.shoppingCart.Dto.CategoryDto;
import com.Online.Shopping.shoppingCart.Entity.Category;
import com.Online.Shopping.shoppingCart.Mapper.CategoryMapper;
import com.Online.Shopping.shoppingCart.Repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	private static final String UPLOAD_DRI=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\Categoryimages\\";
	
	public CategoryDto createCategory(String name,MultipartFile image) throws IOException
	{
		Path path=Paths.get(UPLOAD_DRI,image.getOriginalFilename());
		Files.createDirectories(path.getParent());
		Files.write(path,image.getBytes());
		Category category=new Category();
		category.setName(name);
		category.setImage("Categoryimages/"+image.getOriginalFilename());
		repository.save(category);
		return CategoryMapper.toDto(category);
		
	}
	public String deleteCategory(String name)
	{
		System.out.println(name);
		Optional<Category> category=repository.findByName(name);
		System.out.println(category+" "+repository.findByName(name));
		if(category.isPresent())
		{
			 repository.deleteByName(name);
			 return "deleted category"+name;
		}
		return "Category Not Found";
		
	}
	
	public List<CategoryDto> getCategory() {
		List<Category> categories=repository.findAll();
		
		return categories.stream().map(CategoryMapper::toDto).collect(Collectors.toList());
	}
	public CategoryDto updateCategory(long id, String name,MultipartFile image) throws IOException {
		// TODO Auto-generated method stub
		Optional<Category> c=repository.findById(id);
		if(c.isPresent())
		{
			Path path=Paths.get(UPLOAD_DRI,image.getOriginalFilename());
			Files.write(path,image.getBytes());
			Category category=c.get();
			category.setName(name);
			category.setImage("Categoryimages/"+image.getOriginalFilename());
			repository.save(category);
			return CategoryMapper.toDto(category);

		}
		throw new UsernameNotFoundException("Category not found");
	}
	public CategoryDto getCategory(long id) {
		// TODO Auto-generated method stub
		Category category=repository.findById(id).get();
		System.out.println(category);
		return CategoryMapper.toDto(category);
	}
		



}
