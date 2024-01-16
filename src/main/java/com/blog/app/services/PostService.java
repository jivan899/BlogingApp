package com.blog.app.services;

import java.util.List;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponce;

public interface PostService {

	//create
	
	PostDto cteatePost(PostDto postDto,Long userId,Long cateGoryId);
	
	//update
	
	PostDto updatePost(PostDto postDto,Long id);
	//delete
	
	void deletePost(Long id);
	
	//getById
	PostDto getPostById(Long id);
	
	//getAll
	PostResponce getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);
	
	//getAll post by category
	PostResponce getAllPostByCategory(Long categoryId,Integer pageNumber,Integer pageSize);
	
	//Get all post by User
	PostResponce getAllPostByUser(Long userId,Integer pageNumber, Integer pageSize);
	
	//search post
	List<PostDto> searchPost(String keyWord);
}
