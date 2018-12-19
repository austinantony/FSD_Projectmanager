package com.fsd.project.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.project.entity.Project;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestProjectRepo {

	@Autowired
	ProjectRepository projectRepo;

	@Test
	public void testGetProjectById() throws Exception {
		String projectId = projectRepo.save(new Project("Test Project", new Date(), new Date(), 10)).getProjectId();
		Optional<Project> actual = projectRepo.findById(projectId);
		assertEquals("Test Project", actual.get().getProject());
	}

	@Test
	public void testAddProject() throws Exception{
		String projectId = projectRepo.save(new Project("Test Project1", new Date(), new Date(), 10)).getProjectId();
		Optional<Project> actual = projectRepo.findById(projectId);
		assertEquals("Test Project1", actual.get().getProject());
	}

	@Test
	public void testUpdateProject() throws Exception{
		String projectId = projectRepo.save(new Project("Test Project2", new Date(), new Date(), 10)).getProjectId();
		Optional<Project> actual = projectRepo.findById(projectId);
		actual.get().setProject("Modified Project");
		projectRepo.save(actual.get());
		assertEquals("Modified Project", actual.get().getProject());
	}

	@Test
	public void testDeleteProject() {
		String projectId = projectRepo.save(new Project("Test Project3", new Date(), new Date(), 10)).getProjectId();
		projectRepo.deleteById(projectId);
	    Optional<Project> actual = projectRepo.findById(projectId);
	    assertEquals(Optional.empty(), actual);
	}

}
