package com.blog.app.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponce {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private Long totalElements;
	private int totalPages;
	private Boolean lastPage;
}
