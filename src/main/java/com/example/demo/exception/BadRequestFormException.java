package com.example.demo.exception;

public class BadRequestFormException extends Exception {
	private static final long serialVersionUID = 1L; 
	
	public BadRequestFormException(String message){
		super(message + " Your request is uneabled!!");
	}
}
