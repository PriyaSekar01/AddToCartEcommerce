package com.amazon.project.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.amazon.project.dto.LoginDto;
import com.amazon.project.dto.signUprequestDto;

import com.amazon.project.response.APIResponse;
import com.amazon.project.service.UserService;



@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signUp")
	public ResponseEntity<APIResponse> signUp(@RequestBody signUprequestDto signUpDto){
        APIResponse response = userService.createUser(signUpDto);
		return ResponseEntity.status(response.getStatus()).body(response);
		
	}
	@PostMapping("/login")
	public ResponseEntity<APIResponse> loginUser(@RequestBody LoginDto loginDto ){
        APIResponse response = userService.loginUser(loginDto);
		return ResponseEntity.status(response.getStatus()).body(response);
		}
	
	
	
	
	

}
