package com.krish.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krish.activiti.entity.Person;

public interface HungerRepository extends JpaRepository<Person, Long>{

	Person findByNameIgnoreCase(String name);
	
	Person findByProcessInstanceId(String instanceId);
}
