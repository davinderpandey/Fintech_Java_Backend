package com.fintech.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.fintech.dto.ResponseDTO;
import com.fintech.dto.UserDTO;
import com.fintech.entity.User;
import com.fintech.mapper.UserMapper;
import com.fintech.repository.UserRepository;
import com.fintech.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	private UserMapper userMapper;

	public UserServiceImpl(UserMapper mapper) {
		this.userMapper = mapper;
	}

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				logger.info("Fething user by email ", username);
				return userRepository.findByEmail(username);
			}
		};
	}

	@Override
	public ResponseDTO saveUser(UserDTO userDto) {
		User user = userMapper.mapToUser(userDto);
		user = userRepository.save(user);
		userDto = userMapper.mapToUserDto(user);
		return ResponseDTO.builder().data(userDto).message("User saved successfully").status(HttpStatus.CREATED)
				.success(true).build();
	}

	@Override
	public ResponseDTO getAllUser() {
		List<User> users = userRepository.findAll();
		return ResponseDTO.builder().data(userMapper.mapToUserDtos(users)).message("All Users fetched successfully")
				.status(HttpStatus.OK).success(true).build();
	}

	@Override
	public ResponseDTO getUserById(UUID userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		User user = userOptional.get();
		return ResponseDTO.builder().data(userMapper.mapToUserDto(user)).message("User by id fetch successfully")
				.status(HttpStatus.OK).success(true).build();
	}
	
	@Override
	public ResponseDTO getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		// TODO: need to ignore in mapper
		user.setPassword(null);
		return ResponseDTO.builder().data(userMapper.mapToUserDto(user)).message("User by email fetch successfully")
				.status(HttpStatus.OK).success(true).build();
	}
}