package com.blog.app.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exeptions.ResourceNotFoundException;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponce;
import com.blog.app.repositorys.CategoryRepo;
import com.blog.app.repositorys.PostRepository;
import com.blog.app.repositorys.UserRepo;
import com.blog.app.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto cteatePost(PostDto postDto,Long userId,Long categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setUser(user);
		post.setCategory(category);
		post.setImgName("DefaultImage");
		post.setCreatedOn(new Date());
		
		Post newPost = this.postRepository.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		
		Post post = this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", id));
		post.setTitle(postDto.getTitle());
		post.setContennt(postDto.getContennt());
		post.setImgName(postDto.getImgName());
		Post savedPost = this.postRepository.save(post);
		PostDto dto = this.modelMapper.map(savedPost, PostDto.class);
		return dto;
	}

	@Override
	public void deletePost(Long id) {
		Post post = this.postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", id));
		this.postRepository.delete(post);
	}

	@Override
	public PostDto getPostById(Long id) {
		Post post = this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", id));
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponce getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {
		
		Sort sort = (sortDirection.equalsIgnoreCase("ASC"))?sort = Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		
//		if(sortDirection.equalsIgnoreCase("ASC")) {
//			sort = Sort.by(sortBy).ascending();
//		}else {
//			sort = Sort.by(sortBy).descending();
//		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pageOfPost = this.postRepository.findAll(pageable);
		List<Post> allPost = pageOfPost.getContent();
		List<PostDto> postDtoList = allPost.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		PostResponce postResponce = new PostResponce();
		postResponce.setContent(postDtoList);
		postResponce.setPageNumber(pageOfPost.getNumber());
		postResponce.setPageSize(pageOfPost.getSize());
		postResponce.setTotalElements( pageOfPost.getTotalElements());
		postResponce.setTotalPages(pageOfPost.getTotalPages());
		postResponce.setLastPage(pageOfPost.isLast());
		return postResponce;
	}

	
	@Override
	public PostResponce getAllPostByCategory(Long categoryId,Integer pageNumber,Integer pageSize) {
	    Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
	    Pageable pageable = PageRequest.of(pageNumber,pageSize);
	    Page<Post> pageOfPost = this.postRepository.findByCategory(cat,pageable);
	    List<Post> allPost = pageOfPost.getContent();
	    List<PostDto> postDtoList = allPost.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	    PostResponce postResponce = new PostResponce();
	    postResponce.setContent(postDtoList);
		postResponce.setPageNumber(pageOfPost.getNumber());
		postResponce.setPageSize(pageOfPost.getSize());
		postResponce.setTotalElements( pageOfPost.getTotalElements());
		postResponce.setTotalPages(pageOfPost.getTotalPages());
		postResponce.setLastPage(pageOfPost.isLast());
	    return postResponce;
	}


	@Override
	public PostResponce getAllPostByUser(Long userId,Integer pageNumber, Integer pageSize) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id" , userId));
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Post> pageOfPost = this.postRepository.findByUser(user,pageable);
		List<Post> allPost = pageOfPost.getContent();
		List<PostDto> postDtoList = allPost.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponce postResponce = new PostResponce();
		postResponce.setContent(postDtoList);
		postResponce.setPageNumber(pageOfPost.getNumber());
		postResponce.setPageSize(pageOfPost.getSize());
		postResponce.setTotalElements( pageOfPost.getTotalElements());
		postResponce.setTotalPages(pageOfPost.getTotalPages());
		postResponce.setLastPage(pageOfPost.isLast());
	    return postResponce;
	}

	@Override
	public List<PostDto> searchPost(String keyWord) {
		List<Post> posts = this.postRepository.findByTitleContaining(keyWord);
		 List<PostDto> postDto = posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		 return postDto;
	}

}
