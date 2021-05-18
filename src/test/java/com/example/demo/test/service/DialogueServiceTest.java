package com.example.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.DialogueService;

@SpringBootTest
public class DialogueServiceTest {
	@Autowired
	DialogueService dialogueService;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 */
	
	//getDiarogueTalks
	//正常　申請されているユーザー
	//正常 過去・ラストトークインデックスの更新無
	//正常 範囲外で0件の取得
	//正常 トークルームのラストトークインデックス以下で、ユーザーのラストトークインデックスが更新される
	//正常 トークルームのラストトークインデックスを超えて、ユーザーのラストトークインデックスが更新される
	//異常 加入してない
}
