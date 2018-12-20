package com.fsd.project.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.project.dto.TaskDTO;
import com.fsd.project.entity.ParentTask;
import com.fsd.project.entity.Task;
import com.fsd.project.entity.User;
import com.fsd.project.repository.ParentTaskRepository;
import com.fsd.project.repository.ProjectRepository;
import com.fsd.project.repository.TaskRepository;
import com.fsd.project.repository.UserRepository;
import com.fsd.project.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ParentTaskRepository parentRepository;

	@Override
	public List<Task> getAllTasks() {
		logger.info(" : Logging in TaskServiceImpl.getAllTasks : ");
		return taskRepository.findAll();
	}

	@Override
	public Task getTaskById(String taskId) {
		logger.info(" : Logging in TaskServiceImpl.getTaskById - taskId : " + taskId);
		return taskRepository.getOne(taskId);
	}

	@Transactional
	@Override
	public Task addTask(TaskDTO input, boolean flag) {
		logger.info(" : Logging in TaskServiceImpl.addTask : ");
		if (flag) {
			ParentTask ptask = new ParentTask();
			ptask.setParentTask(input.getTask());
			ptask = parentRepository.saveAndFlush(ptask);
			logger.info(" : Logging in TaskServiceImpl added Parent Task : ");
			return new Task();
		} else {
			User user = userRepository.getOne(input.getUserId());
			user.setProjectId(projectRepository.getOne(input.getProjectId()));
			Task tasknew = new Task();
			tasknew.setEndDate(input.getEndDate());
			tasknew.setPriority(input.getPriority());
			tasknew.setStartDate(input.getStartDate());
			tasknew.setStatus("N");
			tasknew.setTask(input.getTask());
			tasknew.setProjectId(projectRepository.getOne(input.getProjectId()));
			tasknew.setParentTaskId(parentRepository.getOne(input.getParentId()));
			user.setTaskId(tasknew);
			User entUser = userRepository.saveAndFlush(user);
			logger.info(" : Logging in TaskServiceImpl added Task : ");
			return entUser.getTaskId();
		}
	}

	@Transactional
	@Override
	public Task updateTask(TaskDTO input, boolean flag) {
		logger.info(" : Logging in TaskServiceImpl.updateTask : ");
		if (flag) {
			ParentTask ptask = parentRepository.getOne(input.getParentId());
			ptask.setParentTask(input.getTask());
			ptask = parentRepository.saveAndFlush(ptask);
			logger.info(" : Logging in TaskServiceImpl added Parent Task : ");
			return new Task();
		} else {
			User user = userRepository.getOne(input.getUserId());
			Task tasknew = user.getTaskId();
			tasknew.setEndDate(input.getEndDate());
			tasknew.setPriority(input.getPriority());
			tasknew.setStartDate(input.getStartDate());
			tasknew.setTask(input.getTask());
			user.setTaskId(tasknew);
			userRepository.saveAndFlush(user);
			logger.info(" : Logging in TaskServiceImpl Updated Task : ");
			return tasknew;
		}
	}

	@Transactional
	@Override
	public Task endTask(String taskId) {
		logger.info(" : Logging in TaskServiceImpl.endTask : ");
		Task task = taskRepository.getOne(taskId);
		task.setStatus("D");
		return taskRepository.saveAndFlush(task);
	}

	@Override
	public List<ParentTask> getAllParentTasks() {
		logger.info(" : Logging in TaskServiceImpl.getAllParentTasks : ");
		return parentRepository.findAll();
	}

}
