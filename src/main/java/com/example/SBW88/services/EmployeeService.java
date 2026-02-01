package com.example.SBW88.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SBW88.entity.Employee;
import com.example.SBW88.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public List<Employee>getAllEmployees() {
		return empRepo.findAll();
	}

}
