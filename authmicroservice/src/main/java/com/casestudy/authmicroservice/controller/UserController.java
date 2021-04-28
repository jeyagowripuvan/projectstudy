package com.casestudy.authmicroservice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.authmicroservice.model.AuthResponse;
import com.casestudy.authmicroservice.model.UserModel;
import com.casestudy.authmicroservice.model.UserToken;
import com.casestudy.authmicroservice.service.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/health")
	public ResponseEntity<?> healthCheckup() {
		LOGGER.info("Health Check ");
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<UserToken> login(@RequestBody UserModel user) {
		// gets login details, sends it to service class & return login credentials
		LOGGER.info("Inside Login controller");
		return new ResponseEntity<UserToken>(userServiceImpl.login(user), HttpStatus.OK);
	}

	@GetMapping("/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") String token) {
		// gets authorization token & checks for validity
		LOGGER.info("Inside Token Validation controller");
		return new ResponseEntity<AuthResponse>(userServiceImpl.getValidity(token), HttpStatus.OK);
	}

	@GetMapping("/getuser/{name}")
	public UserModel getUser(@PathVariable("name") String userName) {
		LOGGER.info("Inside get user controller");
		return userServiceImpl.getUser(userName);
	}

	@PostMapping("/register")
	public ResponseEntity<UserModel> register(@Valid @RequestBody UserModel user) {
		LOGGER.info("Inside register controller");
		return new ResponseEntity<UserModel>(userServiceImpl.addUser(user), HttpStatus.OK);
	}
}
