package com.example.demo.exception;

public class NotJoinGroupException 	extends Exception {
	private static final long serialVersionUID = 1L; 
	
	public NotJoinGroupException(){
		super("This user is not inserted group");
	}
}