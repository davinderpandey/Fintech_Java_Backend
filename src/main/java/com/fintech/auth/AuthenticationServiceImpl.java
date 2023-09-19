package com.fintech.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fintech.dto.ResponseDTO;
import com.fintech.dto.UserDTO;
import com.fintech.entity.User;
import com.fintech.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@Override
	public ResponseDTO signup(UserDTO request) {
		try {
			
			Role role = Role.USER;
			if(StringUtils.isNoneBlank(request.getRole())) {
				role = Role.valueOf(request.getRole());
			}
			var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
					.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
					.role(role).build();
			userRepository.save(user);
			return ResponseDTO.builder().success(true).message("User created successfully.").status(HttpStatus.CREATED)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDTO.builder().success(false).message("Error while creating user :" + e.getMessage())
					.status(HttpStatus.OK).build();
		}
	}

	@Override
	public JwtAuthenticationToken signin(UserDTO request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = userRepository.findByEmail(request.getEmail());
		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationToken.builder().token(jwt).email(user.getEmail()).build();
	}
}