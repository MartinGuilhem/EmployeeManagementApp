package com.example.SimpleHTTPService.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SimpleHTTPService.Models.Employee;
import com.example.SimpleHTTPService.Models.Employee.Job;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findAllByJob(Job job);

}
