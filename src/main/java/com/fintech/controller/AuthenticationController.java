package com.fintech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.auth.AuthenticationService;
import com.fintech.auth.JwtAuthenticationToken;
import com.fintech.dto.ResponseDTO;
import com.fintech.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService authenticationService;

	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> signup(@RequestBody UserDTO request) {
		return ResponseEntity.ok(authenticationService.signup(request));
	}

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationToken> signin(@RequestBody UserDTO request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}
	
	@GetMapping("/testApi")
	public ResponseEntity<String> checkApi() {
		return ResponseEntity.ok("Api works!");
	}
}