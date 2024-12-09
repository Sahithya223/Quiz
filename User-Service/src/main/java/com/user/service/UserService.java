package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.model.LoginRequest;
import com.user.model.LoginResponse;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public String createUser(User user) {
		if (user.getPassword().equals(user.getConfirmPassword())) {
			userRepository.save(user);
			return user.getUsername() + " successfully created";
		} else
			return "password doesn't match. please enter correct password";
	}

	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	public User getUserById(int id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	public String updateUser(User user)
	{
		Optional<User> user1= userRepository.findById(user.getUserId());
		if(user1.isPresent())
		{
			userRepository.save(user);
			return "user updated successfully";
		}
		else
			return "user not found with Id "+user.getUserId();
	}
	
	public LoginResponse login(LoginRequest loginRequest)
	{
		User user=userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
		LoginResponse loggedInUser= new LoginResponse();
		loggedInUser.setUserId(user.getUserId());
		loggedInUser.setFirstName(user.getFirstName());
		loggedInUser.setLastName(user.getLastName());
		loggedInUser.setEmail(user.getEmail());
		loggedInUser.setRole(user.getRole());
		return loggedInUser;
	}
	public String deleteUser(int id)
	{
		userRepository.deleteById(id);
		return "successfully deleted";
	}

}
