package com.example.demo.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.AlreadyUsedUserIdNameException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.form.UserForm;
import com.example.demo.repository.UserEntityMapper;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserService;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
class UserServiceTest {
	@Autowired
	UserService userService;
	@Autowired
	UserEntityMapper userEntityMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//insertUser
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/UserServiceTest/T01.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T01_insertUser_1() {
		//テストパラメータ作成
		final Integer USER_ID = 5;
		final String USER_ID_NAME = "tester_spesial";
		final String PASSWORD = "test1";
		var form = new UserForm();
		form.setUserIdName(USER_ID_NAME);
		form.setUserName("test");
		form.setPassword(PASSWORD);
		
		//実行
		try {
			userService.insertUser(form);
			
			//パスワードのチェック
			UserEntity entity = userEntityMapper.selectByPrimaryKey(USER_ID);
			assertTrue(passwordEncoder.matches(PASSWORD, entity.getPassword()));
			
		} catch (AlreadyUsedUserIdNameException e) {
			Assertions.assertTrue(false);
		}
	}
	
	//insertUser
	//異常　既にユーザーID名が使われている
	@Test
	@Transactional
	void T02_insertUser_2() {
		//テストパラメータ作成
		final String USER_ID_NAME = "2";
		var form = new UserForm();
		form.setUserIdName(USER_ID_NAME);
		form.setUserName("test");
		form.setPassword("test");
		
		//実行
		assertThrows(AlreadyUsedUserIdNameException.class ,
				() -> userService.insertUser(form));
	}
	
	
	//getUser
	//正常
	@Test
	@Transactional
	void T03_getUser_1() {
		//テストパラメータ作成
		final String USER_ID_NAME = "1";
		var expect = new UserEntity();
		expect.setUserId(1);
		expect.setUserIdName(USER_ID_NAME);
		expect.setUserName("1");
		expect.setIsEnabled(true);
		
		//実行
		try {
			UserEntity result = userService.getUser(USER_ID_NAME);
			assertThat(result).isEqualTo(expect);
			
		} catch (NotFoundException e) {
			Assertions.assertTrue(false);
		}
	}
	
	//getUser
	//異常　存在しない
	@Test
	@Transactional
	void T04_getUser_2() {
		//テストパラメータ作成
		final String USER_ID_NAME = "nothing_user";
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> userService.getUser(USER_ID_NAME));
	}
	
	//getUser
	//異常　削除されてる
	@Test
	@Transactional
	void T05_getUser_3() {
		//テストパラメータ作成
		final String USER_ID_NAME = "4";
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> userService.getUser(USER_ID_NAME));
	}
	
	
	//updateUserIdName
	//正常 
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/UserServiceTest/T06.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T06_updateUserIdName_1() {
		//テストパラメータ作成
		final String USER_ID_NAME = "tester";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			userService.updateUserIdName(user, USER_ID_NAME);
		} catch (AlreadyUsedUserIdNameException e) {
			Assertions.assertTrue(false);
		}
	}
	
	//updateUserName
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/UserServiceTest/T07.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T07_updateUserName_1() {
		//テストパラメータ作成
		final String USER_NAME = "tester";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		userService.updateUserName(user, USER_NAME);
		Assertions.assertTrue(true);
	}
	
	//updatePassword
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/UserServiceTest/T08.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T08_updatePassword_1() {
		//テストパラメータ作成
		final String PASSWORD = "tester";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		userService.updatePassword(null, PASSWORD);
		
		//パスワードのチェック
		UserEntity entity = userEntityMapper.selectByPrimaryKey(USER_ID);
		assertTrue(passwordEncoder.matches(PASSWORD, entity.getPassword()));
	}
	
	//deleteUser
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/UserServiceTest/T09.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T09_deleteUser_1() {
		//テストパラメータ作成
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		userService.deleteUser(user);
		Assertions.assertTrue(true);
	}
}