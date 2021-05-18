package com.example.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.UserInGroupService;

@SpringBootTest
public class UserInGroupServiceTest {
	@Autowired
	UserInGroupService userInGroupService;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 */
	
	//insertUserInGroup
	//正常
	//異常 存在しないグループ
	//異常　すでに加入してるユーザーが指定された
	//異常 既に申請が出ている
	
	//getUsersInGroup
	//正常
	//異常　存在しないグループ
	
	//deleteUserInGroup
	//正常
	//異常　すでに加入してるユーザーが指定された
	
	//exitGroup
	//正常
	//異常 存在しないグループ
}
