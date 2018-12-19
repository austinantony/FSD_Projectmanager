package com.fsd.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.fsd.project.entity.Project;
import com.fsd.project.service.ProjectService;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "*")
@EnableWebMvc
public class ProjectController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProjectService projectService;

	@GetMapping
	public List<Project> getAllProjects() {
		logger.info(" : Logging in ProjectController.getAllProjects method");
		return projectService.getAllProjects();
	}

	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") String projectId) {
		logger.info(" : Logging in ProjectController.getProjectById - Projectid : " + projectId);
		return projectService.getProjectById(projectId);
	}

	@PostMapping("/{id}")
	public Project createProject(@PathVariable("id") String userId,
			@RequestBody Project project) {
		logger.info(" : Logging in ProjectController.createProject - userId : " + userId);
		return projectService.addProject(project, userId);
	}

	@PutMapping("/{id}")
	public Project updateProject(@PathVariable("id") String userId,
			@RequestBody Project project) {
		logger.info(" : Logging in ProjectController.updateProject - userId : " + userId);
		return projectService.updateProject(project, userId);
	}

	@DeleteMapping("/{id}")
	public Project suspendProject(@PathVariable(value = "id") String projectId) {
		logger.info(" : Logging in ProjectController.deleteProject - projectId: " + projectId);
		return projectService.suspendProject(projectId);
	}

}