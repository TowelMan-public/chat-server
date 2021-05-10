package com.example.demo.exception;

public class LoginException extends Exception{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L; 
	
	public LoginException(String message){
		super(message);
	}
}