package com.example.demo.exception;

public class UnEnableException extends Exception {
	private static final long serialVersionUID = 1L; 
	
	private static final String MESSAGE_TEMPLATE = "{{entity}} is not found";
	private static final String ENTITY = "{{entity}}";
	
	public UnEnableException(String entityName){
		super(MESSAGE_TEMPLATE.replace(ENTITY, entityName));
	}
}