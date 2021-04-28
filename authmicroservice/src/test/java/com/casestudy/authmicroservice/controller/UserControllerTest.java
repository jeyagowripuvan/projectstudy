package com.casestudy.authmicroservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.casestudy.authmicroservice.jwt.JwtUtil;
import com.casestudy.authmicroservice.model.AuthResponse;
import com.casestudy.authmicroservice.model.UserModel;
import com.casestudy.authmicroservice.model.UserToken;
import com.casestudy.authmicroservice.repository.UserRepository;
import com.casestudy.authmicroservice.service.UserServiceImpl;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {
	
	@InjectMocks
	UserController userController;

	AuthResponse authResponse;

	UserDetails userdetails;

	@Mock
	JwtUtil jwtutil;

	@Mock
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Test
	public void loginTest()
	{
		UserModel userLoginCredentials = new UserModel(1,"admin","admin","admin", null, "admin");
		UserToken userToken= new UserToken("token","admin",1);
		when(userServiceImpl.login(userLoginCredentials)).thenReturn(userToken);
		assertEquals(userController.login(userLoginCredentials).getBody(),userToken);
	}

	@Test
	public void validityTest()
	{
		AuthResponse auth = new AuthResponse("admin", 1, true);
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(auth,HttpStatus.OK);
		when(userServiceImpl.getValidity("bearer token")).thenReturn(auth);
		assertEquals(userController.getValidity("bearer token"),response);
	}
	
	@Test
	public void getUserIdTest()
	{
		UserModel user=new UserModel(1,"admin","admin","admin", null, "admin");
		when(userServiceImpl.getUser("admin")).thenReturn(user);
		assertEquals(userController.getUser("admin"),user);
	}
	
	@Test
	public void registerTest()
	{
		UserModel user=new UserModel(1,"admin","admin","admin", null, "admin");
		when(userServiceImpl.addUser(user)).thenReturn(user);
		assertEquals(userController.register(user).getBody(),user);
	}
}