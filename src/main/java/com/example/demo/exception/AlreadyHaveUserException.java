package com.example.demo.exception;

public class AlreadyHaveUserException extends Exception {
	private static final long serialVersionUID = 1L; 
	
	public AlreadyHaveUserException(){
		super("This user is already inserted in diarogue by you");
	}
}