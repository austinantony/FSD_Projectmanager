package com.fsd.project.service;

import java.util.List;

import com.fsd.project.dto.TaskDTO;
import com.fsd.project.entity.ParentTask;
import com.fsd.project.entity.Task;

public interface TaskService{

	List<Task> getAllTasks();
	Task getTaskById(String taskId);
	Task addTask(TaskDTO input , boolean flag);
	Task updateTask(TaskDTO input, boolean flag);
	Task endTask(String taskId);
	List<ParentTask> getAllParentTasks();

}
