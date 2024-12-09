package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.LoginRequest;
import com.user.model.LoginResponse;
import com.user.model.User;
import com.user.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("signup")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		String s = userService.createUser(user);
		return new ResponseEntity<String>(s, HttpStatus.CREATED);
	}

	@GetMapping("all")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping("get/{userId}")
	public ResponseEntity<User> getUser(@PathVariable int userId) {
		User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
	{
		LoginResponse loggedUser=userService.login(loginRequest);
		return new ResponseEntity<LoginResponse>(loggedUser,HttpStatus.OK);
	}
	
	@PutMapping("update")
	public ResponseEntity<String> updateUser(@RequestBody User user)
	{
		String s= userService.updateUser(user);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{userId}")
	public ResponseEntity<String> removeUser(@PathVariable int userId)
	{
		String s= userService.deleteUser(userId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@GetMapping("getbyEmail/{email}")
	public ResponseEntity<User> getByEmail(@PathVariable String email)
	{
		User user= userService.getUserByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
