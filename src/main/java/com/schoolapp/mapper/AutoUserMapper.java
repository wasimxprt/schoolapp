package com.schoolapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.schoolapp.dto.UserDto;
import com.schoolapp.entity.User;

@Mapper
public interface AutoUserMapper {

	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

	UserDto mapToUserDto(User user);

	User mapToUser(UserDto userDto);
}
