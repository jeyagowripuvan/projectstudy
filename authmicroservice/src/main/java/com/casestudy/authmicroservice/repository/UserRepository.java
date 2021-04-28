package com.casestudy.authmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.authmicroservice.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String>{
	
	public UserModel findByUserName(String username);
	public UserModel findByUserId(int id);
}
