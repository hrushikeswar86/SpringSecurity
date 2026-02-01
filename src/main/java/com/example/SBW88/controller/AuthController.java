package com.example.SBW88.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SBW88.dto.LoginDto;
import com.example.SBW88.dto.RegisterDto;
import com.example.SBW88.entity.Employee;
import com.example.SBW88.services.AuthService;
import com.example.SBW88.services.JwtService;


@RestController
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtService jwtService;
	
	private static final Logger logger =
	        LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/register")
	public Employee register(@RequestBody RegisterDto regDto) {
		return authService.register(regDto);
	}
	
	@GetMapping("/login")
	public String login(@RequestBody LoginDto loginDto) {
		logger.info(loginDto.getEmail());
		Employee e=authService.authenicate(loginDto);
		logger.info(e.getEmail());
		return jwtService.generateToken(e);
	}
}
