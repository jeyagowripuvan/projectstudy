package com.casestudy.authmicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTokenTest {

	UserToken userToken = new UserToken();
	UserToken userToken1 = new UserToken("ad","ad",1);
	
	@Test
	void testUserName() {
		userToken.setUserName("a");
		assertEquals(userToken.getUserName(), "a");
	}
	
	@Test
	void testAuthToken() {
		userToken.setToken("a");
		assertEquals(userToken.getToken(), "a");
	}
	
	@Test
	void testUserId() {
		userToken.setUserId(1);
		assertEquals(userToken.getUserId(), 1);
	}
}