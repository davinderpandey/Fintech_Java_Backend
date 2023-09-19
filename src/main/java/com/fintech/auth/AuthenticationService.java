package com.fintech.auth;

import com.fintech.dto.ResponseDTO;
import com.fintech.dto.UserDTO;

public interface AuthenticationService {
	
	ResponseDTO signup(UserDTO request);

	JwtAuthenticationToken signin(UserDTO request);
}
