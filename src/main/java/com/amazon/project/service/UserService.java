package com.amazon.project.service;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.amazon.project.dto.LoginDto;
import com.amazon.project.dto.signUprequestDto;
import com.amazon.project.entity.User;
import com.amazon.project.repository.UserRepository;
import com.amazon.project.response.APIResponse;
import com.amazon.project.util.JwtUtils;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public APIResponse createUser(signUprequestDto signUpDto) {
		APIResponse response = new APIResponse();
		User user =  new User();
		user.setName(signUpDto.getName());
		user.setUserGender(signUpDto.getUserGender());
		user.setEmail(signUpDto.getEmail());
		user.setMobileNumber(signUpDto.getMobileNumber());
		user.setPassWord(signUpDto.getPassWord());
		user=userRepository.save(user);
		String token = jwtUtils.generateJwt(user);
		response.setStatus(HttpStatus.OK.value());
		response.setData(token);
		response.setMessage("Register SuccessFully");
		return response;
	}
	
	public APIResponse loginUser(LoginDto loginDto) {
		APIResponse response = new APIResponse();
		User user = userRepository.findByEmailAndPassWord(loginDto.getEmail(),loginDto.getPassWord());
		 if (user != null && user.getEmail().equals(loginDto.getEmail()) && user.getPassWord().equals(loginDto.getPassWord())) {
		        String token = jwtUtils.generateJwt(user);
		        response.setStatus(HttpStatus.OK.value());
		        response.setData(token);
		        response.setMessage("login successful");
		    } else {
		        response.setStatus(HttpStatus.UNAUTHORIZED.value());
		        response.setMessage("Invalid email or password");
		    }
        return response;
		}
	
	public User getByEmail(String email) {
		return userRepository.getByEmail(email);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
		
		
		}
		
	
	
	
	


