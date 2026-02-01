package com.example.SBW88.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SBW88.dto.LoginDto;
import com.example.SBW88.dto.RegisterDto;
import com.example.SBW88.entity.Employee;
import com.example.SBW88.repository.EmployeeRepository;

@Service
public class AuthService {

	@Autowired
	EmployeeRepository empRep;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authManger;
	
	
	public Employee register(RegisterDto registerDto) {
		Employee e=new Employee();
		e.setName(registerDto.getName());
		e.setEmail(registerDto.getEmail());
		e.setRole(registerDto.getRole());
		e.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		return empRep.save(e);
		
	}
	
	public Employee authenicate(LoginDto loginDto) {
		authManger.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		return empRep.findByEmail(loginDto.getEmail());
	}
}
