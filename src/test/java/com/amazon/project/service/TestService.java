package com.amazon.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.amazon.project.controller.UserController;
import com.amazon.project.dto.LoginDto;
import com.amazon.project.dto.signUprequestDto;
import com.amazon.project.entity.User;
import com.amazon.project.repository.UserRepository;
import com.amazon.project.response.APIResponse;
import com.amazon.project.util.JwtUtils;

@SpringBootTest
public class TestService {
	
	 @Mock
	    private UserRepository userRepository;

	    @Mock
	    private JwtUtils jwtUtils;

	    @InjectMocks
	    private UserService userService;

		
	 
	    @Test
	    public void testCreateUser() {
	        signUprequestDto signUpDto = new signUprequestDto();
	        signUpDto.setName("priya");
	        signUpDto.setUserGender("female");
	        signUpDto.setEmail("priya@gmail.com");
	        signUpDto.setMobileNumber("8754665244");
	        signUpDto.setPassWord("priya0103");

	        User userEntity = new User(signUpDto.getName(), signUpDto.getUserGender(), signUpDto.getEmail(),
	                signUpDto.getMobileNumber(), signUpDto.getPassWord());

	        when(userRepository.save(any(User.class))).thenReturn(userEntity);
	        when(jwtUtils.generateJwt(any(User.class))).thenReturn("mockedToken");

	        APIResponse result = userService.createUser(signUpDto);

	        // Additional logging for debugging
	        System.out.println("Actual Result Message: " + result.getMessage());
	        System.out.println("User Service Save Method Called: " + Mockito.verify(userRepository, times(1)).save(any(User.class)));

	        assertThat(result).isNotNull();
	        assertEquals(HttpStatus.OK.value(), result.getStatus());
	        assertEquals("mockedToken", result.getData());
	        assertEquals("Register SuccessFully", result.getMessage().trim());
	    }

        @Test
	    void testLoginUser() {
	        
	        LoginDto loginDto = new LoginDto();
	        loginDto.setEmail("testUser");
	        loginDto.setPassWord("testPassword");

	        User userEntity = new User(loginDto.getEmail(), loginDto.getPassWord());
	        when(userRepository.findByEmailAndPassWord(loginDto.getEmail(), loginDto.getPassWord())).thenReturn(userEntity);
	        when(jwtUtils.generateJwt(userEntity)).thenReturn("mockedToken");
	        APIResponse result = userService.loginUser(loginDto);
	        verify(userRepository).findByEmailAndPassWord(loginDto.getEmail(), loginDto.getPassWord());
	        assertThat(result).isNotNull();
	        assertEquals(HttpStatus.OK.value(), result.getStatus());
	        assertEquals("mockedToken", result.getData());
	        assertEquals("login successful", result.getMessage());
	    }
	}



