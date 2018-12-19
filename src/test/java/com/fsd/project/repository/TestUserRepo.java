package com.fsd.project.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

import com.fsd.project.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestUserRepo {

	@Autowired
	UserRepository userRepo;
	
	@Test
	public void testFindAllUsers() throws Exception{
		userRepo.save(new User("Amit", "Chaudhary",  "E4146788"));
		userRepo.save(new User("Sumit", "Kimling",  "A4146788"));
		userRepo.save(new User("Karna", "Tinkle",  "T4146788"));
		List<User> actual = userRepo.findAll();
		assertThat(actual.size(), is(3));
	}

	@Test
	public void testGetUserById() throws Exception {
		String userId = userRepo.save(new User("Austin", "Antony",  "609742")).getUserId();
		Optional<User> actual = userRepo.findById(userId);
		assertEquals("Antony", actual.get().getLastName());
	}

	@Test
	public void testAddUser() throws Exception{
		String userId = userRepo.save(new User("Austin", "Antony",  "609742")).getUserId();
		Optional<User> actual = userRepo.findById(userId);
		assertEquals("609742", actual.get().getEmployeeId());
	}

	@Test
	public void testUpdateUser() throws Exception{
		String userId = userRepo.save(new User("Antony", "Austin",  "609742")).getUserId();
		Optional<User> actual = userRepo.findById(userId);
		actual.get().setFirstName("Austin");
		actual.get().setLastName("Antony");
		userRepo.save(actual.get());
		assertEquals("Austin", actual.get().getFirstName());
	}

	@Test
	public void testDeleteUser() {
		String userId = userRepo.save(new User("Austin", "Antony",  "609742")).getUserId();
		userRepo.deleteById(userId);
	    Optional<User> actual = userRepo.findById(userId);
	    assertEquals(Optional.empty(), actual);
	}

}
