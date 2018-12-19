package com.fsd.project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import com.fsd.project.service.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProjectController.class })
@WebAppConfiguration
public class TestProjectController {

	MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

	@MockBean
	ProjectService projectService;

	@Test
	public void testFindAllProjects() {
		try {
			this.mockMvc.perform(get("/project").content("{\"project\":\"Test Project\"}").contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetProjectById() {
		try {
			this.mockMvc.perform(get("/project/{id}", 1).content("{\"project\":\"Test Project1\"}").contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddProject() {
		try {
			this.mockMvc.perform(post("/project/{id}", 1).content("{\"project\":\"Test Project2\"}").contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateProject() {
		try {
			this.mockMvc
					.perform(put("/project/{id}", 1).content("{\"project\":\"Modified Project\"}")
							.contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_Delete_Project() {
		try {
			this.mockMvc.perform(delete("/project/{id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}