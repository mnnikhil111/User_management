package com.jsp.ums.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	//For_dto
//	@PostMapping("/users")
//	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody UserRequest userRequest) {
//		return userService.saveUser(userRequest);
//	}
	
	
	
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody @Valid User user) {
		return userService.saveUser(user);
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable int userId,@RequestBody User user)
	{
		return userService.updateUser(userId,user);
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<ResponseStructure<User>> findUser(@PathVariable int userId) {
		return userService.findUser(userId);
	}
	
	//Delete the record by Id
	@DeleteMapping("/{userId}")
	public ResponseEntity<ResponseStructure<User>> deleteStudent(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}
	
	
}
