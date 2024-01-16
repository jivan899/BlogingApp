package com.blog.app.services;

import java.util.List;

import com.blog.app.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto userDto);

	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Long userId);
	UserDto getUserById(Long id);
	List<UserDto> getAllUsers();
	void deleteUser(Long id);
}
