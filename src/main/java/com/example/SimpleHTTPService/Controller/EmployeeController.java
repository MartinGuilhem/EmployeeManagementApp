package com.example.SimpleHTTPService.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.SimpleHTTPService.Models.Employee;
import com.example.SimpleHTTPService.Repositories.EmployeeRepository;
import com.example.SimpleHTTPService.Models.Employee.Job;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	// Add employees to list
	@PostMapping("/add")
	public String addEmployees() {
		List<Employee> Staff = new ArrayList<Employee>();
		Staff.add(new Employee(null, "Santiago", Job.DIRECTOR));
		Staff.add(new Employee(null, "Miguel", Job.MANAGER));
		Staff.add(new Employee(null, "Guillermo", Job.SUPERVISOR));
		Staff.add(new Employee(null, "Martin", Job.SUPERVISOR));
		Staff.add(new Employee(null, "Maialen", Job.EMPLOYEE));
		Staff.add(new Employee(null, "Julieta", Job.EMPLOYEE));
		employeeRepository.saveAll(Staff);
		return "Employees added";
	}

	// Get all employees
	@GetMapping("/staff")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// add new Employee
	@PostMapping("/new")
	public String newEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return employee.getJob() + " " + employee.getName() + " added \n" + employee.toString();
	}
	
	//get Employee by id
	@GetMapping("/employee/{id}")
	public String getEmployee(@PathVariable(name="id") Long id) {
		Employee employee = employeeRepository.findById(id).get();
		return employee.toString();
	}

	// Update employee
	@PutMapping("/employee/{id}")
	public String updateEmployee(@PathVariable(name = "id") Long id, @RequestBody Employee employee) {
		Employee employeeToUpdate = employeeRepository.findById(id).get();

		employeeToUpdate.setName(employee.getName());
		employeeToUpdate.setJob(employee.getJob());
		employeeToUpdate.setSalary(employee.getSalary());
		employeeRepository.save(employeeToUpdate);
		return employee.getJob() + " " + employee.getName() + " updated \n" + employee.toString();
	}

	// Delete employee
	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable(name = "id") Long id) {
		Employee employee = employeeRepository.findById(id).get();
		employeeRepository.deleteById(id);
		return employee.getJob() + " " + employee.getName() + " deleted \n" + employee.toString();
	}

	// Get employees with same job category
	@GetMapping("/staff/{job}")
	public List<Employee> getJobs(@PathVariable(name = "job") Job job) {
		return employeeRepository.findAllByJob(job);
	}
	
	
	//Handling requests errors
	
	//Employee not found handling error - 500
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoSuchElementException.class)
    public String NoEmployeeFound(){
        return "EMPLOYEE NOT FOUND (500 - INTERNAL SERVER ERROR)";
    }
	
	//Invalid number ID handling error - 400
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String invalidId(){
        return "INVALID REQUEST (400 - BAD REQUEST)";
    }  

}
