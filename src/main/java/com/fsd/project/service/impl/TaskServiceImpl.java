package com.fsd.project.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.project.dao.TaskDAO;
import com.fsd.project.dto.TaskDTO;
import com.fsd.project.entity.ParentTask;
import com.fsd.project.entity.Task;
import com.fsd.project.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TaskDAO taskDAO;

	@Override
	public List<Task> getAllTasks() {
		logger.info(" : Logging in TaskServiceImpl.getAllTasks : ");
		return taskDAO.getAllTasks();
	}

	@Override
	public Task getTaskById(String taskId) {
		logger.info(" : Logging in TaskServiceImpl.getTaskById - taskId: " + taskId);
		return taskDAO.getTaskById(taskId);
	}

	@Override
	public Task addTask(TaskDTO input, boolean flag) {
		logger.info(" : Logging in TaskServiceImpl.addTask - input: " + input.getUserId());
		return taskDAO.addTask(input , flag);
	}

	@Override
	public Task updateTask(TaskDTO input, boolean flag) {
		logger.info(" : Logging in TaskServiceImpl.updateTask - input: " + input.getUserId());
		return taskDAO.updateTask(input, flag);
	}

	@Override
	public Task endTask(String taskId) {
		logger.info(" : Logging in TaskServiceImpl.endTask - taskId: " + taskId);
		return taskDAO.endTask(taskId);
	}

	@Override
	public List<ParentTask> getAllParentTasks() {
		logger.info(" : Logging in TaskServiceImpl.getAllParentTasks : ");
		return taskDAO.getAllParentTasks();
	}

}
