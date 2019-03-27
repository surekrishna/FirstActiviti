package com.krish.activiti.controller;

import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krish.activiti.entity.Person;
import com.krish.activiti.service.HungerService;

@RestController
@RequestMapping("/hunger")
public class HungerController {
	
	@Autowired
	private HungerService hungerService;
	
	/**
	 * 
	 * @param assignee should pass user-name(assignee)
	 * @return
	 */
	@GetMapping("/start-process/{assignee}")
	public String startProcessInstance(@PathVariable String assignee) {
		String result = hungerService.startProcess(assignee);
		System.out.println("Start Process :: " + result);
		return result;
	}
	
	/**
	 * 
	 * @param id should pass process-instance-id
	 * @return
	 */
	@GetMapping("/tasks/process-instance/{id}")
	public String getAllTasksByProcessInstanceId(@PathVariable String id) {
		String result = hungerService.getAllTaskByProcessInstanceId(id);
		return result;
	}
	
	/**
	 * 
	 * @param assignee should pass user-name
	 * @return
	 */
	@GetMapping("/tasks/{assignee}")
	public String getTasks(@PathVariable String assignee){
		List<Task> tasks = hungerService.getTasks(assignee);
		System.out.println("Task List {} " + tasks);
		return tasks.toString();
	}
	
	/**
	 * 
	 * @param id should pass task-id
	 * @return
	 */
	@GetMapping("/complete-task/{id}")
	public String completeTask(@PathVariable String id) {
		hungerService.completeTask(id);
		return "Task with id = " + id + "completed successfully.";
	}
	
	/**
	 * 
	 * @param taskId should pass task-id
	 * @param userId should pass user-name
	 */
	@GetMapping("/change-assignee/{taskId}/{userId}")
	public void changeAssignee(@PathVariable String taskId, @PathVariable String userId) {
		hungerService.changeAssignee(taskId, userId);
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/persons")
	public String getAllPersons() {
		List<Person> pList = hungerService.getAllPersons();
		return pList.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/assignees")
	public String getAllAssignees() {
		String result = hungerService.getAllAssignees();
		return result;
	}

}
