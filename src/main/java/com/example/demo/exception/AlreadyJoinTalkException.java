package com.example.demo.exception;

public class AlreadyJoinTalkException extends Exception {
	private static final long serialVersionUID = 1L; 
	
	public AlreadyJoinTalkException(String message){
		super(message);
	}
}
