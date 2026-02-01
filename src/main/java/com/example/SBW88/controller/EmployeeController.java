package com.example.SBW88.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SBW88.entity.Employee;
import com.example.SBW88.services.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/employees")
	public String getEmployees() {
		return "List of Employees";
	}
	
	@GetMapping("/home")
	public String goToHome() {
		return "List of Employees";
	}
	
	@GetMapping("/employees/all")
	public List<Employee> getAllEmployees() {
		return empService.getAllEmployees();
	}
}
