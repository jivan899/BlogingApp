package com.blog.app.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.app.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Long postId;
	
//	@NotBlank
//	@Size(min = 10)
	private String title;
//	
//	@NotBlank
//	@Size(min = 15)
	private String contennt;
	
	private String imgName;
	
	private Date createdOn;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
