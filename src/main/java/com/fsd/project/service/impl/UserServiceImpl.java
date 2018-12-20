package com.fsd.project.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.project.entity.User;
import com.fsd.project.repository.UserRepository;
import com.fsd.project.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		logger.info(" : Logging in UserServiceImpl.getAllUsers : ");
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String userId) {
		logger.info(" : Logging in UserServiceImpl.getUserById - userId : " + userId);
		return userRepository.getOne(userId);
	}

	@Override
	public void addUser(User user) {
		logger.info(" : Logging in UserServiceImpl.addUser : ");
		userRepository.saveAndFlush(user);
	}

	@Override
	public void updateUser(User user) {
		logger.info(" : Logging in UserServiceImpl.updateUser method : ");
		User existing = getUserById(user.getUserId());
		if (existing != null) {
			user.setUserId(existing.getUserId());
			user.setTaskId(existing.getTaskId());
			user.setProjectId(existing.getProjectId());
		}
		userRepository.saveAndFlush(user);
	}

	@Override
	public void deleteUser(String userId) {
		logger.info(" : Logging in UserServiceImpl.deleteUser method : ");
		userRepository.deleteById(userId);
	}

}