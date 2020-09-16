package com.example.SimpleHTTPService.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	
	public enum Job {
		EMPLOYEE, SUPERVISOR, MANAGER, DIRECTOR;
	}
	
	
	
	//ATRIBUTES 
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	String name;
	@Column
	Job job;
	@Column
	Double salary;
	
	
	public Employee() {}
	
	public Employee(Long id, String name, Job job) {
		this.id=id;
		this.name=name;
		this.job=job;
		this.salary=calculateSalary(job);
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = calculateSalary(job);
	}

	

	final Double baseSalary = 1000.00;
	
	public Double calculateSalary(Job job) {
		switch(job) {
		case EMPLOYEE:
			salary = baseSalary;
			break;
		case SUPERVISOR:
			salary = baseSalary*1.2;
			break;
		case MANAGER:
			salary = baseSalary*1.5;
			break;
		case DIRECTOR:
			salary = baseSalary*2;
			break;
		}
		return salary;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", Position=" + job + ", salary=" + salary + "]";
	}
	
	

}
