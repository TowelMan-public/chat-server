package com.example.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.UserDetailsServiceImp;

@SpringBootTest
public class UserDetailsServiceImpTest {
	@Autowired
	UserDetailsServiceImp userDetailsServiceImp;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 */
	
	//loadUserByUserIdName
	//正常
	//異常 存在しない
	//異常 削除されてる
	
	//loadUserByUserId
	//正常
	//異常　存在しない
}
