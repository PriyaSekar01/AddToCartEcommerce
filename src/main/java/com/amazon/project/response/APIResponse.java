package com.amazon.project.response;




import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class APIResponse {
	
	private int status;
	
	private Object data;

	private String message;
	
	
     public APIResponse(int status, Object data, Object error, String messege) {
		super();
		this.status = status;
		this.data = data;
		
		this.message = message;
	}



	
	
	

}
