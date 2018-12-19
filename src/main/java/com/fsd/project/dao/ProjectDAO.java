package com.fsd.project.dao;

import java.util.List;

import com.fsd.project.entity.Project;

public interface ProjectDAO {

	List<Project> getAllProjects();
	Project getProjectById(String projectId);
	Project addProject(Project project, String userId);
	Project updateProject(Project project , String userId);
	Project suspendProject(String projectId);

}