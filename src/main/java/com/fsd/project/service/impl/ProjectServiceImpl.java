package com.fsd.project.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.project.entity.Project;
import com.fsd.project.entity.User;
import com.fsd.project.repository.ProjectRepository;
import com.fsd.project.repository.UserRepository;
import com.fsd.project.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<Project> getAllProjects() {
		logger.info(" : Logging in ProjectServiceImpl.getAllProjects : ");
		return projectRepository.findAll();
	}

	@Override
	public Project getProjectById(String projectId) {
		logger.info(" : Logging in ProjectServiceImpl.getProjectById : " + projectId);
		return projectRepository.getOne(projectId);
	}

	@Override
	public Project addProject(Project project, String userId) {
		logger.info(" : Logging in ProjectServiceImpl.addProject - userId : " + userId);
		User user = userRepository.getOne(userId);
		user.setProjectId(project);
		User newUser = userRepository.saveAndFlush(user);
		return newUser.getProjectId();
	}

	@Override
	public Project updateProject(Project project, String userId) {
		logger.info(" : Logging in ProjectServiceImpl.updateProject - userId : " + userId);
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

	@Override
	public Project suspendProject(String projectId) {
		logger.info("Logging in ProjectServiceImpl.deleteProject - projectId : " + projectId);
		userRepository.findByProjectId(projectRepository.getOne(projectId)).forEach(user -> {
			user.setProjectId(null);
			userRepository.saveAndFlush(user);
		});
		return projectRepository.getOne(projectId);
	}

}