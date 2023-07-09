package com.schoolapp.service;


import java.util.List;

import com.schoolapp.dto.UserDto;
import com.schoolapp.entity.User;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto getUserById(Long id);
	
	List<UserDto> getAllUsers();
	
	UserDto updateUser(UserDto user);
	
	void deleteUser(Long userId);
}
