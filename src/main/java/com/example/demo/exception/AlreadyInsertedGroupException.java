package com.example.demo.exception;

public class AlreadyInsertedGroupException extends Exception{
	private static final long serialVersionUID = 1L; 
	
	public AlreadyInsertedGroupException(){
		super("User is already inserted in this group!");
	}
}