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
import com.schoolapp.exception.EmailAlreadyExistsException;
import com.schoolapp.exception.ResourceNotFoundException;
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

		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

		if (optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email Already Exists");
		}

		User user = AutoUserMapper.MAPPER.mapToUser(userDto);

		User savedUser = userRepository.save(user);

		// UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

		// UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
		UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		// User user = optionalUser.get();
		// return UserMapper.mapToUserDto(user);
		// return modelMapper.map(user, UserDto.class);
		return AutoUserMapper.MAPPER.mapToUserDto(user);
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
		User existingUser = userRepository.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));
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

		userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userRepository.deleteById(userId);
	}

}
