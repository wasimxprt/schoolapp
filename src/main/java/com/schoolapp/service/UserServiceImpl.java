package com.schoolapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.aot.AutowiredMethodArgumentsResolver;
import org.springframework.stereotype.Service;

import com.schoolapp.dto.UserDto;
import com.schoolapp.entity.User;
import com.schoolapp.mapper.AutoUserMapper;
import com.schoolapp.mapper.UserMapper;
import com.schoolapp.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		// convert UserDto to User JPA Entity
		// User user = UserMapper.mapToUser(userDto);

		// User user = modelMapper.map(userDto, User.class);

		User user = AutoUserMapper.MAPPER.mapToUser(userDto);

		User savedUser = userRepository.save(user);

		// UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

		// UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
		UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		User user = optionalUser.get();
		// return UserMapper.mapToUserDto(user);
		// return modelMapper.map(user, UserDto.class);
		return AutoUserMapper.MAPPER.mapToUserDto(optionalUser.get());
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		// return
		// users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
		// return users.stream().map((user) -> modelMapper.map(user,
		// UserDto.class)).collect(Collectors.toList());
		return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = userRepository.findById(user.getId()).get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(existingUser);
		// return UserMapper.mapToUserDto(updatedUser);
		// return modelMapper.map(updatedUser, UserDto.class);
		return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}

}
