package com.blog.app.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.config.AppConstants;
import com.blog.app.payloads.ApiResponce;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponce;
import com.blog.app.payloads.UserDto;
import com.blog.app.services.FileService;
import com.blog.app.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost
	(
			@RequestBody PostDto postDto,
			@PathVariable Long userId,
			@PathVariable Long categoryId){
		
		PostDto createdPost = this.postService.cteatePost(postDto,userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	//Get Post By id
	@GetMapping("/post/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
		PostDto postById = this.postService.getPostById(id);
		return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
	}
	
	//Delete Post
	@DeleteMapping("/deletePost/{id}")
	public ApiResponce deletePost(@PathVariable Long id){
		this.postService.deletePost(id);
		return new ApiResponce("Post is successfully Deleted",true);
	}
	
	//update Post
	@PutMapping("/update/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Long id){
		PostDto updatePost = this.postService.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//get All Post
	@GetMapping("/allPost")
	public ResponseEntity<PostResponce> getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIR,required = false) String sortDirection
			
			){
		 PostResponce postResponce = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDirection);
		return new ResponseEntity<PostResponce>(postResponce,HttpStatus.OK);
	}
	
	//get post by category Id
	@GetMapping("/category/{categoryId}/postByCat")
	public ResponseEntity<PostResponce> getPostByCat(
			@PathVariable Long categoryId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSze
			){
		PostResponce postResponce = this.postService.getAllPostByCategory(categoryId,pageNumber,pageSze);
		return new ResponseEntity<PostResponce>(postResponce,HttpStatus.OK);
	}
	
	
	//get post by User Id
	@GetMapping("/user/{userId}/postByUser")
	public ResponseEntity<PostResponce> getPostByUserId(
			@PathVariable Long userId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize
			){
		PostResponce postResponce = this.postService.getAllPostByUser(userId,pageNumber,pageSize);
		return new ResponseEntity<PostResponce>(postResponce,HttpStatus.OK); 
	}
	
	//search
	@GetMapping("/post/search/{keyWord}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyWord){
		List<PostDto> result = this.postService.searchPost(keyWord);
//							OR
//		List<PostDto> result2 = this.postService.searchPost("%"+keyWord+"%");
		
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	//Image
	//Post image Upload
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Long postId) throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		
		postDto.setImgName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//get Image
	@GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void dwonloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
}
