package com.fsd.project.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.project.dao.UserDAO;
import com.fsd.project.entity.User;
import com.fsd.project.repository.UserRepository;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		logger.info(" : Logging in UserDAOImpl.getAllUsers : ");
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String userId) {
		logger.info(" : Logging in UserDAOImpl.getUserById - userId : " + userId);
		return userRepository.getOne(userId);
	}

	@Override
	public void addUser(User user) {
		logger.info(" : Logging in UserDAOImpl.addUser : ");
		userRepository.saveAndFlush(user);
	}

	@Override
	public void updateUser(User user) {
		logger.info(" : Logging in UserDAOImpl.updateUser method : ");
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
		logger.info(" : Logging in UserDAOImpl.deleteUser method : ");
		userRepository.deleteById(userId);
	}

}