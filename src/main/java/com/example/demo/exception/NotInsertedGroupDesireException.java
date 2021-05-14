package com.example.demo.exception;

public class NotInsertedGroupDesireException extends Exception {
	private static final long serialVersionUID = 1L; 
	
	public NotInsertedGroupDesireException(){
		super("This user is not inserted 'Desire Group'");
	}
}