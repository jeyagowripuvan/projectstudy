package com.casestudy.authmicroservice.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.casestudy.authmicroservice.repository.UserRepository;
import com.casestudy.authmicroservice.exception.UnauthorisedException;
import com.casestudy.authmicroservice.jwt.JwtUtil;
import com.casestudy.authmicroservice.model.AuthResponse;
import com.casestudy.authmicroservice.model.UserModel;
import com.casestudy.authmicroservice.model.UserToken;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String userName)  {
		// all actions to check the correctness of the userID
		LOGGER.info("Inside loadbyusername service");
		UserModel user = userRepository.findByUserName(userName);
		LOGGER.info("user has been loaded");
			return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

	public UserToken login(UserModel userModel)  {
		// validate password & return token
		final UserDetails userdetails = loadUserByUsername(userModel.getUserName());
		UserToken userToken = new UserToken();
		if (userdetails.getPassword().equals(userModel.getPassword())) {
			LOGGER.info("Inside login service");
			LOGGER.info("user is valid");
			userToken.setUserName(userModel.getUserName());
			userToken.setUserId(userRepository.findByUserName(userModel.getUserName()).getUserId());
			userToken.setToken(jwtUtil.generateToken(userdetails));
			LOGGER.info("user token has been generated");
			return userToken;
		} else {
			LOGGER.error("User not found");
			throw new UnauthorisedException("Invalid username or password");
		}
	}

	public AuthResponse getValidity(String token) {
		LOGGER.info("Inside get validity service");
		String token1 = token.substring(7);
		AuthResponse authResponse = new AuthResponse();
		if (jwtUtil.validateToken(token1)) {
			LOGGER.info("Token is valid");
			authResponse.setUsername(jwtUtil.extractUsername(token1));
			authResponse.setValid(true);
			authResponse.setId(userRepository.findByUserName(jwtUtil.extractUsername(token1)).getUserId());
			LOGGER.info("user is validated");
		} else {
			authResponse.setValid(false);
			LOGGER.error("Token Has Expired");
		}
		return authResponse;

	}
	
	@Override
	public UserModel getUser(String userName) {
		LOGGER.info("Inside getuser service");
		return userRepository.findByUserName(userName);
	}

	@Override
	public UserModel addUser(UserModel user) {
		LOGGER.info("Inside adduser service");
		userRepository.save(user);
		return user;
	}
}
