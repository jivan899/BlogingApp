package com.blog.app.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private int categoryId;
	
	@NotBlank
	@Size(min=3,message = "Please enter atList 3 charecter")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10,message = "Please enter atlist 10 charecter")
	private String categoryDescription;
}
