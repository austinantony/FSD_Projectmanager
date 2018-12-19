package com.fsd.project.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.project.dao.ProjectDAO;
import com.fsd.project.entity.Project;
import com.fsd.project.entity.User;
import com.fsd.project.repository.ProjectRepository;
import com.fsd.project.repository.UserRepository;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	@Override
	public Project getProjectById(String projectId) {
		logger.info(" : Logging in getProjectById - projectId : " + projectId);
		return projectRepository.getOne(projectId);
	}

	@Transactional
	@Override
	public Project addProject(Project project, String userId) {
		logger.info(" : Logging in addProject - userId : " + userId);
		User user = userRepository.getOne(userId);
		user.setProjectId(project);
		User newUser = userRepository.saveAndFlush(user);
		return newUser.getProjectId();
	}

	@Transactional
	@Override
	public Project updateProject(Project project, String userId) {
		logger.info(" : Logging in updateProject - userId : " + userId);
		Project existing = getProjectById(project.getProjectId());
		if (existing != null) {
			project.setProjectId(existing.getProjectId());
			User user = userRepository.getOne(userId);
			user.setProjectId(project);
			User newUser = userRepository.saveAndFlush(user);
			return newUser.getProjectId();
		}
		return existing;
	}

	@Transactional
	@Override
	public Project suspendProject(String projectId) {
		logger.info(" : Logging in deleteProject - projectId : " + projectId);
		userRepository.findByProjectId(projectRepository.getOne(projectId)).forEach(user -> {
			user.setProjectId(null);
			userRepository.saveAndFlush(user);
		});
		return projectRepository.getOne(projectId);
	}
}