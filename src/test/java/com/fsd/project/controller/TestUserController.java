package com.fsd.project.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import com.fsd.project.entity.User;
import com.fsd.project.service.UserService;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserController.class })
@WebAppConfiguration
public class TestUserController {

	MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	@MockBean
	UserService userService;

	@Test
	public void testFindAllUsers() throws Exception {
		mockMvc.perform(get("/user")).andExpect(status().isOk());
	}
	
	@Test
	public void testUserService() {
		List<User> list = userService.getAllUsers();
		assertEquals(list.isEmpty(), true);
	}

	@Test
	public void testGetUserById() throws Exception {
		mockMvc.perform(get("/user/1")).andExpect(status().isOk());
	}

	@Test
	public void testAddUser() throws Exception {
		User user = new User("Austin", "Antony", "609742");
        String json = new Gson().toJson(user);
		
		mockMvc.perform(
                post("/user")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk());
	}

	@Test
	public void testUpdateUser() throws Exception {
		User user = new User("Austin", "Antony", "609742");
        String json = new Gson().toJson(user);
		
		mockMvc.perform(
                put("/user")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk());
	}

	@Test
	public void testDeleteUser() throws Exception {
		this.mockMvc.perform(delete("/user/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}