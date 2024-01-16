package com.blog.app.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.blog.app.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private Long id;
	
	@NotEmpty
	@Size(min=4,message = "username Must be min of 4 charecter")
	private String name;
	
	@Email(message = "Your email address is Not valid!!!")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=8, message = "Password must be min of 3 chars and maximum of 8 chars")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}
