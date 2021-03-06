package com.fsd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsd.project.entity.ParentTask;

@Repository
public interface ParentTaskRepository  extends JpaRepository<ParentTask, String> {

}
