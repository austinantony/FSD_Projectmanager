package com.fsd.project.service;

import java.util.List;

import com.fsd.project.entity.Project;

public interface ProjectService {

	List<Project> getAllProjects();
	Project getProjectById(String projectId);
	Project addProject(Project project, String userId);
	Project updateProject(Project project, String userId);
	Project suspendProject(String projectId);

}