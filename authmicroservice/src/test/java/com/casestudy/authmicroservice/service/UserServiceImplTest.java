package com.casestudy.authmicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.casestudy.authmicroservice.exception.UnauthorisedException;
import com.casestudy.authmicroservice.jwt.JwtUtil;
import com.casestudy.authmicroservice.model.AuthResponse;
import com.casestudy.authmicroservice.model.UserModel;
import com.casestudy.authmicroservice.model.UserToken;
import com.casestudy.authmicroservice.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	JwtUtil jwtUtil;

	@Test
	public void loadUserByUsernameTest() {

		UserModel user1 = new UserModel(1, "admin", "admin","admin", "123", "admin");
		when(userRepository.findByUserName("admin")).thenReturn(user1);
		UserDetails loadUserByUsername2 = userServiceImpl.loadUserByUsername("admin");
		assertEquals(user1.getUserName(), loadUserByUsername2.getUsername());
	}

	@Test
	public void loginTest() {

		UserModel user = new UserModel(1, "admin", "123","admin", "admin", "admin");
		UserToken userToken=new UserToken("admin","admin",1);
		when(userRepository.findByUserName("admin")).thenReturn(user);
		UserDetails value = userServiceImpl.loadUserByUsername("admin");
		when(userRepository.findByUserName("admin")).thenReturn(user);
		when(jwtUtil.generateToken(value)).thenReturn("token");
		UserToken result = userServiceImpl.login(user);
		assertEquals(result.getUserName(),userToken.getUserName());

	}

	@Test 
	public void testGetValidity() 
	{
	  UserModel user = new UserModel(1, "admin", "admin","admin", "admin", "admin");
	  when(jwtUtil.validateToken("token")).thenReturn(true);
	  when(jwtUtil.extractUsername("token")).thenReturn(user.getUserName());
	  when(userRepository.findByUserName(jwtUtil.extractUsername("token"))).thenReturn(user);
	  AuthResponse auth = new AuthResponse("admin",1,true); 
	  ResponseEntity<AuthResponse> response = new ResponseEntity<AuthResponse>(auth,HttpStatus.OK);
	  AuthResponse result =userServiceImpl.getValidity("Bearer token");
	  assertEquals(result.getUsername(), response.getBody().getUsername()); 
	  }
	
	
	  @Test
	  public void testNotValidate()
	  { 
		  when(jwtUtil.validateToken("Bearer token")).thenReturn(false);
		  AuthResponse result=userServiceImpl.getValidity("Bearer token");
		  assertEquals(result.isValid(), false);
	}
	 
	
	@Test
	public void testUserNotFoundException()
	{
		UserModel user1 = new UserModel(1, "abc", "abc","admin", "admin", "admin");
		when(userRepository.findByUserName("admin")).thenReturn(user1);
		assertThrows(UnauthorisedException.class,()->userServiceImpl.login(new UserModel(1,"admin","abs","abc", "admin", "abs")));
	}
	
	@Test
	public void testGetUser()
	{
		UserModel user = new UserModel(1, "admin", "admin","admin", "123", "admin");
		when(userRepository.findByUserName("admin")).thenReturn(user);
		assertEquals(userServiceImpl.getUser("admin").getName(),user.getName());
	}
	
	@Test
	public void testAddUser()
	{
		UserModel user = new UserModel(1, "admin", "admin","admin","123", "admin");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(userServiceImpl.addUser(user).getUserName(),user.getUserName());
	}
}
