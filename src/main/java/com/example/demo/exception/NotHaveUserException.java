package com.example.demo.exception;

public class NotHaveUserException extends Exception {
	private static final long serialVersionUID = 1L; 
	
	public NotHaveUserException(){
		super("This user is not inserted in diarogue");
	}
}