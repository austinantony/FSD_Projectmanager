package com.fsd.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.project.dao.UserDAO;
import com.fsd.project.entity.User;
import com.fsd.project.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO usersDAO;

	@Override
	public List<User> getAllUsers() {
		return usersDAO.getAllUsers();
	}

	@Override
	public User getUserById(String userId) {
		return usersDAO.getUserById(userId);
	}

	@Override
	public void addUser(User user) {
		usersDAO.addUser(user);
	}

	@Override
	public void updateUser(User user) {
		usersDAO.updateUser(user);
	}

	@Override
	public void deleteUser(String userId) {
		usersDAO.deleteUser(userId);
	}

}