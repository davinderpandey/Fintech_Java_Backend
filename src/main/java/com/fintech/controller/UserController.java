package com.fintech.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.dto.ResponseDTO;
import com.fintech.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/{userId}")
	public ResponseDTO getSingleUser(@PathVariable UUID userId) {
		logger.info("Get Single User Handler: UserController");
		return userService.getUserById(userId);
	}
	
	@GetMapping("/getByEmail")
	public ResponseDTO getLoggedInUserDetails(@RequestParam(value = "email") String email) {
		logger.info("Get User by email.");
	    return userService.getUserByEmail(email);
	}

	@GetMapping(value = "/all")
	public ResponseDTO getAllUser() {
		return userService.getAllUser();
	}
}