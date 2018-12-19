package com.fsd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsd.project.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String>{

}
