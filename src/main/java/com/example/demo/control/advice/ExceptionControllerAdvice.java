package com.example.demo.control.advice;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.*;

import lombok.Value;

/**
 * 例外ハンドラー。
 * Controllerクラスまでに発生した例外をキャッチしてエラーレスポンスとしてクライアントに返す。
 */
@RestControllerAdvice
public class ExceptionControllerAdvice{
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse notFoundException(NotFoundException e) {
		return new ErrorResponse("NotFoundException",e.getMessage());
	}
	
	@ExceptionHandler(LoginException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorResponse loginException(LoginException e) {
		return new ErrorResponse("LoginException",e.getMessage());
	}
	
	@ExceptionHandler(AlreadyHaveUserException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse alreadyHaveUserException(AlreadyHaveUserException e) {
		return new ErrorResponse("AlreadyHaveUserException",e.getMessage());
	}
	
	@ExceptionHandler(AlreadyInsertedGroupDesireException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse alreadyInsertedGroupDesireException(AlreadyInsertedGroupDesireException e) {
		return new ErrorResponse("AlreadyInsertedGroupDesireException",e.getMessage());
	}
	
	@ExceptionHandler(AlreadyInsertedGroupException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse alreadyInsertedGroupException(AlreadyInsertedGroupException e) {
		return new ErrorResponse("AlreadyInsertedGroupException",e.getMessage());
	}
	
	@ExceptionHandler(AlreadyUsedUserIdNameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse alreadyUsedUserIdNameException(AlreadyUsedUserIdNameException e) {
		return new ErrorResponse("AlreadyUsedUserIdNameException",e.getMessage());
	}
	
	@ExceptionHandler(NotHaveUserException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse notHaveUserException(NotHaveUserException e) {
		return new ErrorResponse("NotHaveUserException",e.getMessage());
	}
	
	@ExceptionHandler(NotInsertedGroupDesireException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse notInsertedGroupDesireException(NotInsertedGroupDesireException e) {
		return new ErrorResponse("NotInsertedGroupDesireException",e.getMessage());
	}
	
	@ExceptionHandler(NotJoinGroupException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse notJoinGroupException(NotJoinGroupException e) {
		return new ErrorResponse("NotJoinGroupException",e.getMessage());
	}
	
	@ExceptionHandler(BadRequestFormException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse badRequestFormException(BadRequestFormException e) {
		return new ErrorResponse("BadRequestFormException",e.getMessage());
	}
	
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse bindException(BindException e) {
		return new ErrorResponse("BindException",e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
		return new ErrorResponse("MethodArgumentNotValidException",e.getMessage());
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse typeMismatchException(TypeMismatchException e) {
		return new ErrorResponse("TypeMismatchException",e.getMessage());
	}
	
	@Value
	public class ErrorResponse {
	    String errorCode;
	    String message;
	}
}