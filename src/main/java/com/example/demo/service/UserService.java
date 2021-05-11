package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnEnableException;
import com.example.demo.form.UserForm;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;

/**
 * ログインを省くユーザーに関するAPIのサービスロジック
 */
@Service
public class UserService {
	@Autowired
	UserLogic userLogic;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザーを追加する
	 * @param form 追加するユーザー情報
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void insertUser(UserForm form) {
		form.setPassword(
				passwordEncoder.encode(form.getPassword()));
		userLogic.insertUser(form);
	}

	/**
	 * ユーザーId名からユーザ情報の取得
	 * @param userIdName　ユーザーId名
	 * @return ユーザ情報
	 * @throws com.example.demo.exception.NotFoundException ユーザーIdが存在しない
	 * @throws com.example.demo.exception.UnEnableException ユーザーIdが無効なものである
	*/
	public UserEntity getUser(String userIdName) throws NotFoundException, UnEnableException {
		return userLogic.getUserByUserIdName(userIdName);
	}

	/**
	 * ユーザーID名を変更する
	 * @param user アクセスしたユーザーの情報
	 * @param userIdName ユーザーID名
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void updateUserIdName(UserDetailsImp user, String userIdName) {
		userLogic.updateUserIdName(user,userIdName);
	}

	/**
	 * ユーザー名を変更する
	 * @param user アクセスしたユーザーの情報
	 * @param userName ユーザー名
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void updateUserName(UserDetailsImp user, String userName) {
		userLogic.updateUserName(user,userName);
	}

	/**
	 * パスワードの変更
	 * @param user アクセスしたユーザーの情報
	 * @param password 生のパスワード
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void updatePassword(UserDetailsImp user, String password) {
		userLogic.updatePassword(user, passwordEncoder.encode(password));
	}

	/**
	 * ユーザーを削除する（物理的には残ってたりする）
	 * @param user アクセスしたユーザーの情報
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void deleteUser(UserDetailsImp user) {
		userLogic.deleteUser(user);
	}
}
