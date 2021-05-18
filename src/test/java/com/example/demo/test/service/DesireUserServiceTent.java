package com.example.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.DesireUserService;

@SpringBootTest
public class DesireUserServiceTent {
	@Autowired
	DesireUserService desireUserService;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 */
	
	//getDesireUser
	//正常1
	//正常2
	
	//deleteDesireUser
	//正常
	//異常　ユーザー見つからない
	//異常 申請出してない
	
	//joinUser
	//正常
	//異常 ユーザー見つからない（元からない）
	//異常 ユーザー見つからない（削除されてる）
}
