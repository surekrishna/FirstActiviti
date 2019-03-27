package com.krish.activiti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements Serializable {

	/**
	 * added generated serialVersionUID
	 */
	private static final long serialVersionUID = -6993440463857662020L;
	
	@Id
	@SequenceGenerator(name = "PERSON_ID_GENERATOR", sequenceName = "PERSON_ID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PERSON_ID_GENERATOR")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "process_instance_id")
	private String processInstanceId;
	
	public Person() {}
	
	public Person(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", processInstanceId=" + processInstanceId + "]";
	}

}
