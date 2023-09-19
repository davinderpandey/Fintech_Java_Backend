package com.fintech.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String message;
	private boolean success;
	private HttpStatus status;
	private Object data;

}