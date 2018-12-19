package com.fsd.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsd.project.entity.Project;
import com.fsd.project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	public List<User> findByProjectId(Project projectId);
}
