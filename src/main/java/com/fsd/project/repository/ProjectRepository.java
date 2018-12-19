package com.fsd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsd.project.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String>{

}
