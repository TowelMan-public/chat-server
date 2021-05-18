package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;

/**
 * ログインと認証のためのビジネスロジックを書くクラス
 */
@Service
public class UserDetailsServiceImp {

	@Autowired
	UserLogic userLogic;
	
	/**
	 * ユーザーId名からユーザ情報の取得
	 * @param userIdName　ユーザーId名
	 * @return ユーザ情報
	 * @throws com.example.demo.exception.NotFoundException ユーザーIdが存在しない
	*/
	public UserDetailsImp loadUserByUserIdName(String userIdName) throws NotFoundException {
		return new UserDetailsImp(
				userLogic.getUserByUserIdName(userIdName));
	}

	/**
	 * ユーザーIdからユーザ情報の取得
	 * @param userId　ユーザーId
	 * @return ユーザ情報
	 * @throws com.example.demo.exception.NotFoundException ユーザーIdが存在しない
	*/
	public UserDetails loadUserByUserId(Integer userId) throws NotFoundException {		
		return new UserDetailsImp(
				userLogic.getUserByUserId(userId));
	}

}
