package com.example.demo.exception;

public class AlreadyUsedUserIdNameException extends Exception{
	private static final long serialVersionUID = 1L; 
	
	public AlreadyUsedUserIdNameException(){
		super("This userIdname is already used");
	}
}
