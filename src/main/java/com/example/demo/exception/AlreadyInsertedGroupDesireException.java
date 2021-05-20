package com.example.demo.exception;

public class AlreadyInsertedGroupDesireException extends Exception{
	private static final long serialVersionUID = 1L; 
	
	public AlreadyInsertedGroupDesireException(){
		super("User is already inserted in this group desire!");
	}
}