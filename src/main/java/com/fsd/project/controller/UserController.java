package com.fsd.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fsd.project.entity.User;
import com.fsd.project.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@EnableWebMvc
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserService userService;

	@GetMapping
	public List<User> getAllUsers() {
		logger.info(" : Logging in UserController.getAllUsers : ");
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") String userId) {
		logger.info(" : Logging in UserController.getUserById - userId : " + userId);
		return userService.getUserById(userId);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody User user) {
		logger.info("Create UserController.createUser userId : " + user.getFirstName());
		userService.addUser(user);
		return user;
	}

	@PutMapping
	public User updateUser(@RequestBody User user) {
		logger.info(" : Logging in UserController.updateUser - userId : " + user.getUserId());
		userService.updateUser(user);
		return user;
	}

	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable(value = "id") String userId) {
		logger.info(" : Logging in UserController.deleteUser - userId : " + userId);
		userService.deleteUser(userId);
		return new User();
	}

}