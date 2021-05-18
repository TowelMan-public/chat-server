package com.example.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.GroupTalkService;

@SpringBootTest
public class GroupTalkServiceTest {
	@Autowired
	GroupTalkService groupTalkService;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 */
	
	//insertTalk
	//正常
	//異常 グループ加入してない
	
	//getTalk
	//正常
	//異常 存在しないトーク
	
	
	//updateTalk
	//正常
	//異常 存在しないトーク
	
	//deleteTalk
	//正常
	//異常 違うユーザーが作ったトーク
}