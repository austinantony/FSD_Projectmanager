package com.fsd.project.controller;

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
	public void testFindAllTasks() {
		try {
			this.mockMvc.perform(get("/task").content("{\"task\":\"New Task\"}").contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTaskById() {
		try {
			this.mockMvc.perform(get("/task/{id}", 1).content("{\"task\":\"New Task1\"}").contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddTask() {
		try {
			this.mockMvc.perform(post("/task/{flag}", true).content("{\"task\":\"New Task2\"}").contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateTask() {
		try {
			this.mockMvc.perform(put("/task/{flag}", true).content("{\"task\":\"New Task3\"}").contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllParentTasks() {
		try {
			this.mockMvc.perform(get("/task/parent").content("{\"parentTask\":\"Parent Task4\"}")
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
