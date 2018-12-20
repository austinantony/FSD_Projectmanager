package com.fsd.project.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.project.entity.ParentTask;
import com.fsd.project.entity.Project;
import com.fsd.project.entity.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestTaskRepo {

	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	TaskRepository taskRepo;

	@Autowired
	ParentTaskRepository parentRepo;

	
	@Test
	public void testGetTaskById() throws Exception {
		Project project= projectRepo.save(new Project("Test Project1", new Date(), new Date(), 10));
		ParentTask task= parentRepo.save(new ParentTask("Parent Task1"));
		String taskID = taskRepo.save(new Task(project,task, "New Task", new Date(), new Date(), 20, "N")).getTaskId();
		Optional<Task> actual = taskRepo.findById(taskID);
		assertEquals("New Task", actual.get().getTask());
	}

	@Test
	public void testAddTask() throws Exception{
		Optional<Project> project=	projectRepo.findById(projectRepo.save(new Project("Test Project2", new Date(), new Date(), 10)).getProjectId());
		Optional<ParentTask> task=	parentRepo.findById(parentRepo.save(new ParentTask("Parent Task2")).getParentId());
		String taskID = taskRepo.save(new Task(project.get(),task.get(), "New Task1", new Date(), new Date(), 30, "N")).getTaskId();
		Optional<Task> actual = taskRepo.findById(taskID);
		assertEquals("New Task1", actual.get().getTask());
	}

	@Test
	public void testUpdateTask() throws Exception{		
		Optional<Project> project=	projectRepo.findById(projectRepo.save(new Project("Test Project3", new Date(), new Date(), 10)).getProjectId());
		Optional<ParentTask> task=	parentRepo.findById(parentRepo.save(new ParentTask("Parent Task3")).getParentId());
		String taskID = taskRepo.save(new Task(project.get(),task.get(), "New Task3", new Date(), new Date(), 13, "N")).getTaskId();
		Optional<Task> actual = taskRepo.findById(taskID);
		actual.get().setTask("Updated Task");
		taskRepo.save(actual.get());
		assertEquals("Updated Task", actual.get().getTask());
	}

	@Test
	public void testDeleteTask() {
		Optional<Project> project=	projectRepo.findById(projectRepo.save(new Project("Test Project4", new Date(), new Date(), 10)).getProjectId());
		Optional<ParentTask> task=	parentRepo.findById(parentRepo.save(new ParentTask("Parent Task4")).getParentId());
		String taskID = taskRepo.save(new Task(project.get(),task.get(), "New Task4", new Date(), new Date(), 11, "N")).getTaskId();
		taskRepo.deleteById(taskID);
	    Optional<Task> actual = taskRepo.findById(taskID);
	     assertEquals(Optional.empty(), actual);
	}
	
	/*@Test
	public void testGetParentTask() throws Exception{
		parentRepo.findById(parentRepo.save(new ParentTask("Parent Task_1")).getParentId());
		parentRepo.findById(parentRepo.save(new ParentTask("Parent Task_2")).getParentId());
		parentRepo.findById(parentRepo.save(new ParentTask("Parent Task_3")).getParentId());
		List<ParentTask> actual = parentRepo.findAll();
		 assertThat(actual.size(), is(3));
	}*/

}
