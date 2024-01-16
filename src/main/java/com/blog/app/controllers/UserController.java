package com.blog.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.ApiResponce;
import com.blog.app.payloads.UserDto;
import com.blog.app.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	
	//Post call create User
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUser = this.userService.createUser(userDto);
		
		return new ResponseEntity<>(createUser,HttpStatus.CREATED);
	}
	
	//Put call Update User
	
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Long id){
		
		UserDto updatedUser = this.userService.updateUser(userDto,id);
		return ResponseEntity.ok(updatedUser);
		
	}
	

	//Admin
	//delete call delete User
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable Long id) {
		this.userService.deleteUser(id);
		System.out.println("record deleted");
//		return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
		return new ResponseEntity<ApiResponce>(new ApiResponce("User Deleted Successfully",true),HttpStatus.OK);
	}
	
	//Get call get single user
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long id){
		UserDto userById = this.userService.getUserById(id);
		
		return new ResponseEntity<>(userById,HttpStatus.OK);
	}
	
	//Get All Users
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		List<UserDto> allUsers = this.userService.getAllUsers();
		
		return ResponseEntity.ok(allUsers);
	}
	
}
