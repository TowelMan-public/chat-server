package com.example.demo.exception;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = 1L; 
	
	private static final String MESSAGE_TEMPLATE = "{{field}} is not found";
	private static final String FIELD = "{{field}}";
	
	public NotFoundException(String fieldName){
		super(MESSAGE_TEMPLATE.replace(FIELD, fieldName));
	}
}