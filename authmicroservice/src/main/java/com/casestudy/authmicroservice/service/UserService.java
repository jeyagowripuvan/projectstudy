package com.casestudy.authmicroservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.casestudy.authmicroservice.model.AuthResponse;
import com.casestudy.authmicroservice.model.UserModel;
import com.casestudy.authmicroservice.model.UserToken;

public interface UserService extends UserDetailsService {

	UserToken login(UserModel user);
	AuthResponse getValidity(String token);
	UserModel getUser(String userName);
	UserModel addUser(UserModel user);
}

