package com.example.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.GroupService;

@SpringBootTest
public class GroupServiceTest {
	@Autowired
	GroupService groupService;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 */
	
	//getGroup
	//正常
	//異常　存在しないグループ
	
	//getGroups
	//正常
	
	//updateGroupName
	
	
	//getGroupTalks
	//正常　申請されているユーザー
	//正常 過去・ラストトークインデックスの更新無
	//正常 範囲外で0件の取得
	//正常 トークルームのラストトークインデックス以下で、ユーザーのラストトークインデックスが更新される
	//正常 トークルームのラストトークインデックスを超えて、ユーザーのラストトークインデックスが更新される
	//異常　存在しないグループ 削除されてる
	
	//deleteGroup
	//正常
	//異常　存在しないグループ
	
	//insertGroup
	//成功
}