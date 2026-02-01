package com.example.SBW88.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SBW88.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByEmail(String email);
}
