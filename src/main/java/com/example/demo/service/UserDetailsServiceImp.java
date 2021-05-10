package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnEnableException;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;

@Service
public class UserDetailsServiceImp {

	@Autowired
	UserLogic userLogic;
	
	public UserDetailsImp loadUserByUserIdName(String userIdName) throws NotFoundException, UnEnableException {
		return new UserDetailsImp(
				userLogic.getUserByUserIdName(userIdName));
	}

	public UserDetails loadUserByUserId(Integer userId) throws NotFoundException, UnEnableException {		
		return new UserDetailsImp(
				userLogic.getUserByUserId(userId));
	}

}
