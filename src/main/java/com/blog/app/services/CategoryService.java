package com.blog.app.services;

import java.util.List;

import com.blog.app.payloads.CategoryDto;


public interface CategoryService {
	
	//create
	 CategoryDto creteCategory(CategoryDto categoryDto);
	
	//update
	 CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
	
	//delete
	 void deleteCategory(Long categoryId);
	
	
	//get by id
	 CategoryDto getCategoryById(Long categoryId);
	
	//get all
	 List<CategoryDto> getAllCategory();
	
	 

}
