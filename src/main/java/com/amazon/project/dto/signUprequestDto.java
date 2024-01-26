package com.amazon.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class signUprequestDto {
	
	private String name;
	
	private String userGender;
	
	private String email;
	
	private String mobileNumber;
	
	private String passWord;

}
