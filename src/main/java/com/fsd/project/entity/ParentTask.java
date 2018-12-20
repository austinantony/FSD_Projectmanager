package com.fsd.project.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "parent_task")
public class ParentTask implements Serializable {

	private static final long serialVersionUID = 1L;

	private String parentId;
	
	@NotBlank
	@Column(name = "parent_task")
	private String parentTask;
	
	
	private Set<Task> task = new HashSet<Task>(0);

	@OneToMany(mappedBy = "parentTaskId")
	@JsonBackReference("parentObj")
	public Set<Task> getTask() {
		return task;
	}

	public void setTask(Set<Task> task) {
		this.task = task;
	}
		

	@Id
	@Column(name = "parent_id")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	
	

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	public ParentTask(@NotBlank String parentTask) {
		super();
		this.parentTask = parentTask;
	}

	public ParentTask() {
		super();
	}

	
	
	
}
