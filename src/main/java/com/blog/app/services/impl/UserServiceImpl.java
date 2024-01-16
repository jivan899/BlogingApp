package com.blog.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.app.config.AppConstants;
import com.blog.app.entities.Role;
import com.blog.app.entities.User;
import com.blog.app.exeptions.ResourceNotFoundException;
import com.blog.app.payloads.UserDto;
import com.blog.app.repositorys.RoleRepo;
import com.blog.app.repositorys.UserRepo;
import com.blog.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {

		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		this.userRepo.save(user);
		return this.userToDto(user);
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtoList = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		
		return userDtoList;
	}

	@Override
	public void deleteUser(Long id) {

		User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user", "id", id));
		this.userRepo.delete(user);
		System.out.println("User Deleted");
		}
	
	
	
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	private UserDto userToDto(User user) {
		 UserDto userDto = this.modelMapper.map(user, UserDto.class);
		 return userDto;
	}
//	private User dtoToUser(UserDto userDto) {
//		User user = new User();
//		
//		user.setId(userDto.getId());
//		user.setAbout(userDto.getAbout());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
//		return user;
//	}
//
//	private UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
//		return userDto;
//	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		
		//encoded password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//roles
		
		Role role = this.roleRepo.getById(AppConstants.ROLE_NORMAL);//.get();
		user.getRoles().add(role);
		this.userRepo.save(user);

		return  this.modelMapper.map(user, UserDto.class);
	}

}
