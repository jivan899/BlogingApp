package com.blog.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.ApiResponce;
import com.blog.app.payloads.CategoryDto;
import com.blog.app.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCategory(@Validated @RequestBody CategoryDto categoryDto){
		CategoryDto creteCategory = this.categoryService.creteCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(creteCategory,HttpStatus.CREATED);

	}
	
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Validated @RequestBody CategoryDto categoryDto,@PathVariable Long categoryId){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	@GetMapping("/categoryById/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId){
		CategoryDto categoryById = this.categoryService.getCategoryById(categoryId);
		
		return new ResponseEntity<CategoryDto>(categoryById,HttpStatus.OK);
	}
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategory = this.categoryService.getAllCategory();
		
		return  ResponseEntity.ok(allCategory);
	}
	
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<ApiResponce> deleteCategory(@PathVariable Long categoryId) {
		
		this.categoryService.deleteCategory(categoryId);
		System.out.println("Category Deleted");
		return new ResponseEntity<ApiResponce>(new ApiResponce("Category is deleted",true),HttpStatus.OK);
	}
	
	
	
	
	
}
