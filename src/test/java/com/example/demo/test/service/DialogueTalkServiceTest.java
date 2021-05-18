package com.example.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.DialogueTalkService;

@SpringBootTest
public class DialogueTalkServiceTest {
	@Autowired
	DialogueTalkService dialogueTalkService;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 */
	
	//insertTalk
	//正常
	//異常　友達登録してない
	
	//getTalk
	//正常
	//異常 存在しないトーク
	
	
	//updateTalk
	//正常
	//異常 違うユーザーが作ったトーク
	
	//deleteTalk
	//正常
	//異常 違うユーザーが作ったトーク
}