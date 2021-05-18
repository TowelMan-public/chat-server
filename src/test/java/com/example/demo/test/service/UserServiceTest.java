package com.example.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.UserService;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	UserService userService;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 */
	
	//insertUser
	//正常1
	//異常　既にユーザーID名が使われている
	
	//getUser
	//正常1
	//異常　存在しない
	//異常　削除されてる
	
	//updateUserIdName
	//正常 
	
	//updateUserName
	//正常
	
	//updatePassword
	//正常
	
	//deleteUser
	//正常
	
}
