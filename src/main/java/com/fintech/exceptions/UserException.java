package com.fintech.exceptions;

public class UserException extends Exception{

	String message="User not found";
	private static final long serialVersionUID = 1L;
	
	public UserException(String message) {
		super(message);
	}
	
	

}
