package com.casestudy.authmicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserModelTest {

	UserModel user = new UserModel();
	UserModel user1 = new UserModel(1, "ad", "ad","abc", "abc", "123");

	@Test
	void testUserId() {
		user.setUserId(1);
		assertEquals(user.getUserId(), 1);
	}
	
	@Test
	void testEmail() {
		user.setEmail("abc");
		assertEquals(user.getEmail(), "abc");
	}
	
	@Test
	void testMobileNo() {
		user.setMobileNo("123");
		assertEquals(user.getMobileNo(), "123");
	}

	@Test
	void testName() {
		user.setName("abc");
		assertEquals(user.getName(), "abc");
	}
	
	@Test
	void testUserName() {
		user.setUserName("abc");
		assertEquals(user.getUserName(), "abc");
	}
	
	@Test
	void testPassword() {
		user.setPassword("abc");
		assertEquals(user.getPassword(), "abc");
	}
}

