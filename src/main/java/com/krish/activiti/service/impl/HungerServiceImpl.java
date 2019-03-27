package com.krish.activiti.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krish.activiti.entity.Person;
import com.krish.activiti.repository.HungerRepository;
import com.krish.activiti.service.HungerService;

@Service("hungerService")
@Transactional
public class HungerServiceImpl implements HungerService {
	
	@Autowired
	private HungerRepository hungerRepository;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RepositoryService repositoryService;

	@Override
	public Person getByName(String name) {
		return hungerRepository.findByNameIgnoreCase(name);
	}

	@Override
	public void createPersons() {
		
		if(hungerRepository.findAll().isEmpty()) {
			hungerRepository.save(new Person("John"));
			hungerRepository.save(new Person("Mike"));
			hungerRepository.save(new Person("Lucy"));
			hungerRepository.save(new Person("Elizibath"));
			hungerRepository.save(new Person("Krish"));
		}
	}

	@Override
	public String startProcess(String assignee) {
		Person person = hungerRepository.findByNameIgnoreCase(assignee);
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("person", person);
		
		ProcessInstance pInstance = runtimeService.startProcessInstanceByKey("hungerProcess", variables);
		String pInstanceId = pInstance.getProcessInstanceId();
		person.setProcessInstanceId(pInstanceId);
		hungerRepository.save(person);
		return this.getProcessInfo();
	}
	
	private String getProcessInfo() {
		
		List<Task> tList = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();
		
		StringBuilder sBuilder = new StringBuilder();
		
		sBuilder.append("Number of process Definitions --> " + repositoryService.createProcessDefinitionQuery().count() + "\nTask List >>\n");
		
		for(Task task : tList) {
			sBuilder.append(task + " | Assignee : " + task.getAssignee() + " | " + task.getDescription() + " *** ");
		}
		
		return sBuilder.toString();
	}

	@Override
	public List<Task> getTasks(String assignee) {
		List<Task> tList = taskService.createTaskQuery().taskAssigneeLikeIgnoreCase(assignee).list();
		System.out.println("Task List {} " + tList);
		return tList;
	}

	@Override
	public void completeTask(String taskId) {
		taskService.complete(taskId);
	}

	@Override
	public void changeAssignee(String taskId, String userId) {
		taskService.setAssignee(taskId, userId);
		
	}

	@Override
	public List<Person> getAllPersons() {
		return hungerRepository.findAll();
	}

	@Override
	public String getAllAssignees() {
		StringBuilder sBuilder = new StringBuilder();
		List<Task> tList = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();
		System.out.println(tList);
		for(Task task : tList) {
			sBuilder.append(task + " " + task.getAssignee() + " *** ");
		}
		
		return sBuilder.toString();
	}

	@Override
	public String getAllTaskByProcessInstanceId(String id) {
		StringBuilder sBuilder = new StringBuilder();
		List<Task> tList = taskService.createTaskQuery().processInstanceId(id).list();
		System.out.println(tList);
		
		for(Task task : tList) {
			sBuilder.append(task + " " + task.getAssignee() + " *** ");
		}
		return sBuilder.toString();
	}
	
	@Override
	public void hungerSatisified(DelegateExecution delegateExecution) {
		String pInstanceId = delegateExecution.getProcessInstanceId();
		Person person = hungerRepository.findByProcessInstanceId(pInstanceId);
				
		System.out.println("Hunger satisified {} " + person);
	}

}
