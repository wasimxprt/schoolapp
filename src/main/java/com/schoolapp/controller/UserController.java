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

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;

	// create user
	@PostMapping("")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
		UserDto user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto user) {
		user.setId(id);
		UserDto updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
	}

	
}
