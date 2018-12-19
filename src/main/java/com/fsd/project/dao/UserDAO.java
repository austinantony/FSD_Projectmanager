package com.fsd.project.dao;

import java.util.List;

import com.fsd.project.entity.User;

public interface UserDAO {

	List<User> getAllUsers();
	User getUserById(String userId);
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(String userId);
}