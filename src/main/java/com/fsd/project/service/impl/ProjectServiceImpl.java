package com.fsd.project.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.project.dao.ProjectDAO;
import com.fsd.project.entity.Project;
import com.fsd.project.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProjectDAO projectDAO;

	@Override
	public List<Project> getAllProjects() {
		logger.info(" : Logging in ProjectServiceImpl.getAllProjects : ");
		return projectDAO.getAllProjects();
	}

	@Override
	public Project getProjectById(String projectId) {
		logger.info(" : Logging in ProjectServiceImpl.getProjectById : " + projectId);
		return projectDAO.getProjectById(projectId);
	}

	@Override
	public Project addProject(Project project, String userId) {
		logger.info(" : Logging in ProjectServiceImpl.addProject - userId : " + userId);
		return projectDAO.addProject(project, userId);
	}

	@Override
	public Project updateProject(Project project, String userId) {
		logger.info(" : Logging in ProjectServiceImpl.updateProject - userId : " + userId);
		return projectDAO.updateProject(project, userId);
	}

	@Override
	public Project suspendProject(String projectId) {
		logger.info("Logging in ProjectServiceImpl.deleteProject - projectId : " + projectId);
		return projectDAO.suspendProject(projectId);
	}

}