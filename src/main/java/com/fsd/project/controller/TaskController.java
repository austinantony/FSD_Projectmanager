package com.fsd.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fsd.project.dto.TaskDTO;
import com.fsd.project.entity.ParentTask;
import com.fsd.project.entity.Task;
import com.fsd.project.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins="*")
@EnableWebMvc
public class TaskController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TaskService taskService;

	@GetMapping
	public List<Task> getAllTasks() {
		logger.info(" : Logging in TaskController.getAllTasks : ");
		return taskService.getAllTasks();
	}

	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable("id") String taskId) {
		logger.info(" : Logging in TaskController.getTaskById - taskId : " + taskId);
		return taskService.getTaskById(taskId);
	}

	@PostMapping("/{flag}")
	public Task addTask(@PathVariable(value = "flag") boolean flag , @RequestBody TaskDTO task) {
		logger.info(" : Logging in TaskController.createProject - userId : " + task.getUserId());
		return taskService.addTask(task ,flag);
	}

	@PutMapping("/{flag}")
	public  Task updateTask(@PathVariable(value = "flag") boolean flag , @RequestBody TaskDTO task) {
		logger.info(" : Logging in TaskController.updateTask - task : " + task.getUserId());
		return taskService.updateTask(task,flag);
	}

	@PutMapping("/endTask/{id}")
	public Task endTask(@PathVariable(value = "id") String taskId) {
		logger.info(" : Logging in TaskController.endTask - taskId : " + taskId);
		return taskService.endTask(taskId);
	}
	
	@GetMapping("/parent")
	public  @ResponseBody  ResponseEntity<List<ParentTask>> getAllParentTasks() {
		logger.info(" : Logging in TaskController.getAllParentTasks : ");
		List<ParentTask> ptasks = taskService.getAllParentTasks();
		return new ResponseEntity<List<ParentTask>>(ptasks, HttpStatus.OK);
	}


}
