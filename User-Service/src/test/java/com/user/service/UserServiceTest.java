package com.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.model.User;
import com.user.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	
	User user=null;
	
	Optional<User> user1=null;
	
	@BeforeEach
	public void testCreateUser()
	{
		user=new User();
		user.setUserId(1);
		user.setUsername("SahithyaP");
		user.setFirstName("Parnapalli");
		user.setLastName("Sahithya");
		user.setEmail("sahithyaparnapalli@gmail.com");
		user.setRole("user");
		user.setPassword("Sahithya205@");
		user.setConfirmPassword("Sahithya205@");
		System.out.println("before each is called");
		
	}
	
	@Test
	public void testAddUser()
	{
		when(userRepository.save(user)).thenReturn(user);
		String message=userService.createUser(user);
		assertEquals(user.getUsername()+" successfully created", message);
	}
	
	@Test
	public void testPasswordDidnotMatch()
	{
		user.setConfirmPassword("sahithya205@");
		when(userRepository.save(user)).thenReturn(user);
		String message=userService.createUser(user);
		assertEquals("password doesn't match. please enter correct password", message);
	}
	
	@Test
	public void testGetUser()
	{
		user.setConfirmPassword("Sahithya205@");
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		User userDetails=userService.getUserById(user.getUserId());
		assertEquals(user.getUsername(), userDetails.getUsername());
		
	}
	
	@Test
	public void testDeleteUser()
	{
		doNothing().when(userRepository).deleteById(user.getUserId());
		userService.deleteUser(user.getUserId());
		verify(userRepository, times(1));
	}
}
