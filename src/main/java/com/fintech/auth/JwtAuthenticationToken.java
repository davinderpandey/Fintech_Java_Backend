package com.fintech.auth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtAuthenticationToken {
	private String email;
	private String token;
}