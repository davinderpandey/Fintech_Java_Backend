package com.fintech.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.fintech.dto.UserDTO;
import com.fintech.entity.User;


@Mapper(componentModel = "spring")
public abstract class UserMapper {

	public abstract UserDTO mapToUserDto(User user);

	public abstract User mapToUser(UserDTO userDto);

	public abstract List<UserDTO> mapToUserDtos(List<User> users);

}
