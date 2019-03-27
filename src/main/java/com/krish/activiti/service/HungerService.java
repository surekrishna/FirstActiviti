package com.krish.activiti.service;

import java.util.List;

import org.activiti.engine.task.Task;

import com.krish.activiti.entity.Person;

public interface HungerService {
	
	Person getByName(String name);
	
	void createPersons();
	
	String startProcess(String assignee);
	
	List<Task> getTasks(String assignee);
	
	void completeTask(String taskId);
	
	void changeAssignee(String taskId, String userId);
	
	List<Person> getAllPersons();
	
	String getAllAssignees();
	
	String getAllTaskByProcessInstanceId(String id);

}
