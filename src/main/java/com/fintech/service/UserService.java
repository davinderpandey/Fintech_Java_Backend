package com.fintech.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fintech.dto.ResponseDTO;
import com.fintech.dto.UserDTO;


public interface UserService {
	
	UserDetailsService userDetailsService();
	
	ResponseDTO saveUser(UserDTO user);
	
	ResponseDTO getAllUser();
	
	ResponseDTO getUserById(UUID userId);
	
	ResponseDTO getUserByEmail(String email);


}