package com.schoolapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.schoolapp.dto.UserDto;
import com.schoolapp.entity.User;
import com.schoolapp.exception.ErrorDetails;
import com.schoolapp.exception.ResourceNotFoundException;
import com.schoolapp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "CRDU rest apis for User resources", description = "CRUD apis for get, post, put, delete")
@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;

	// create user
	@Operation(summary = "Create user REST api", description = "Create User API is used to save user in the database")
	@ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
	@PostMapping("")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@Operation(summary = "Create user by id REST api", description = "Create User API is used to get a single user from the database")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
		UserDto user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@Operation(summary = "Create all users REST api", description = "Create User API is used to get all users from the database")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@Operation(summary = "Update user REST api", description = "Create User API is used to get all users from the database")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto user) {
		user.setId(id);
		UserDto updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@Operation(summary = "Delete user REST api", description = "Delete User API is used to delete user from the database")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
	}

}
