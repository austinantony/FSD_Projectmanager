package com.fsd.project.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fsd.project.dto.TaskDTO;
import com.fsd.project.entity.Task;
import com.fsd.project.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TaskController.class })
@WebAppConfiguration
public class TestTaskController {

	MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	@MockBean
	TaskService taskService;
	
	@Test
	public void testTaskDTO() {
		TaskDTO task = new TaskDTO();
		task.setUserId("1");
		task.setEndDate(null);
		task.setParentId("P1");
		task.setPriority(10);
		task.setProjectId("PR1");
		task.setStartDate(null);
		task.setStatus("D");
		task.setTask("T1");
		task.toString();
		task.getPriority();
		task.getStartDate();
		task.getEndDate();
		task.getParentId();
		task.getStatus();
		task.getTask();
		assertEquals(task.getParentId(), "P1");
	}
	
	@Test
	public void testTaskService() {
		Task task = taskService.getTaskById("T1");
		assertEquals(task, null);
	}


	@Test
	public void testFindAllTasks() throws Exception {
		this.mockMvc.perform(get("/task").content("{\"task\":\"New Task\"}").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testGetTaskById() throws Exception {
		this.mockMvc.perform(get("/task/{id}", 1).content("{\"task\":\"New Task1\"}").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testAddTask() throws Exception {
		this.mockMvc.perform(post("/task/{flag}", true).content("{\"task\":\"New Task2\"}").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testUpdateTask() throws Exception {
		this.mockMvc.perform(put("/task/{flag}", true).content("{\"task\":\"New Task3\"}").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testEndTask() throws Exception {
		this.mockMvc.perform(put("/task/endTask/{id}", true).content("{\"task\":\"1\"}").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testGetAllParentTasks() throws Exception {
		this.mockMvc.perform(get("/task/parent").content("{\"parentTask\":\"Parent Task4\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
