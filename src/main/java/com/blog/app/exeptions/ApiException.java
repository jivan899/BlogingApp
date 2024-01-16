package com.blog.app.exeptions;

public class ApiException extends RuntimeException{

	public ApiException(String message) {
		super(message);
		
	}

	public ApiException() {
		super();
	}

	
	
}
