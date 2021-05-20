package com.example.demo.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.BadRequestFormException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotJoinGroupException;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.GroupTalkService;

@SpringBootTest
class GroupTalkServiceTest {
	@Autowired
	GroupTalkService groupTalkService;
	
	private static final String TIMESTAMP_STRING_TEMPLATE = "2020/9/9/00/00";
	
	private static final String CONTENT_TEXT_FIREST = "test";
	
	//insertTalk
	//正常
	@Test
	@Transactional
	void T01_insertTalk_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final String TALK_CONTENT_TEXT = "test Hey! you!!";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			groupTalkService.insertTalk(user, GROUP_TALK_ROOM_ID, TALK_CONTENT_TEXT);
			assertTrue(true);
		} catch (NotFoundException | NotJoinGroupException e) {
			assertTrue(false);
		}
	}
	
	//insertTalk
	//異常 グループ加入してない
	@Test
	@Transactional
	void T02_insertTalk_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 7;
		final String TALK_CONTENT_TEXT = "test Hey! you!!";
		final Integer USER_ID = 2;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotJoinGroupException.class ,
				() -> groupTalkService.insertTalk(user, GROUP_TALK_ROOM_ID, TALK_CONTENT_TEXT));
	}
	
	//getTalk
	//正常
	@Test
	@Transactional
	void T03_getTalk_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 7;
		final Integer TALK_INDEX = 2;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		var expect = new TalkResponse();
		expect.setUserIdName("1");
		expect.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		expect.setTalkIndex(2);
		expect.setContent(CONTENT_TEXT_FIREST + 2);
		
		//実行
		try {
		TalkResponse result = groupTalkService.getTalk(user, GROUP_TALK_ROOM_ID, TALK_INDEX);
		assertThat(result).isEqualTo(expect);
		}catch (NotJoinGroupException | NotFoundException e) {
			assertTrue(false);
		}
	}
	
	//getTalk
	//異常 存在しないトーク
	@Test
	@Transactional
	void T04_getTalk_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 7;
		final Integer TALK_INDEX = 500;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> groupTalkService.getTalk(user, GROUP_TALK_ROOM_ID, TALK_INDEX));
	}
	
	//updateTalk
	//正常
	@Test
	@Transactional
	void T05_updateTalk_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 7;
		final String TALK_CONTENT_TEXT = "update! hey!";
		final Integer TALK_INDEX = 1;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			groupTalkService.updateTalk(user, GROUP_TALK_ROOM_ID, TALK_INDEX, TALK_CONTENT_TEXT);
			assertTrue(true);
		} catch (NotJoinGroupException | NotFoundException | BadRequestFormException e) {
			assertTrue(false);
		}
	}
	
	//updateTalk
	//異常 存在しないトーク
	@Test
	@Transactional
	void T06_updateTalk_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 7;
		final String TALK_CONTENT_TEXT = "update! hey!";
		final Integer TALK_INDEX = 500;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> groupTalkService.updateTalk(user, GROUP_TALK_ROOM_ID, TALK_INDEX, TALK_CONTENT_TEXT));
	}
	
	//deleteTalk
	//正常
	@Test
	@Transactional
	void T07_deleteTalk_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 7;
		final Integer TALK_INDEX = 3;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			groupTalkService.deleteTalk(user, GROUP_TALK_ROOM_ID, TALK_INDEX);
			assertTrue(true);
		} catch (NotJoinGroupException | NotFoundException | BadRequestFormException e) {
			assertTrue(false);
		}
	}
	
	//deleteTalk
	//異常 違うユーザーが作ったトーク
	@Test
	@Transactional
	void T08_deleteTalk_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final Integer TALK_INDEX = 1;
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(BadRequestFormException.class ,
				() -> groupTalkService.deleteTalk(user, GROUP_TALK_ROOM_ID, TALK_INDEX));
	}
}