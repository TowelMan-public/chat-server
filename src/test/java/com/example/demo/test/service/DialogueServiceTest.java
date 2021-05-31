package com.example.demo.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotHaveUserException;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.DialogueService;

@SpringBootTest
class DialogueServiceTest {
	@Autowired
	DialogueService dialogueService;
	
	private static final String TIMESTAMP_STRING_TEMPLATE = "2020/9/9/00/00";
	
	private static final String CONTENT_TEXT_FIREST = "test";
	
	//getDiarogueTalks
	//正常　申請されているユーザー
	@Test
	@Transactional
	void T01_getDiarogueTalks_1() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "1";
		final Integer USER_ID = 3;
		final Integer MAX_SIZE = 1;
		final Integer STERT_INDEX = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<TalkResponse> expect = new ArrayList<>();
		var entity = new TalkResponse();
		entity.setUserIdName("1");
		entity.setUserName("1");
		entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		entity.setTalkIndex(1);
		entity.setContent(CONTENT_TEXT_FIREST + "1");
		expect.add(entity);
		
		//実行
		try {
			List<TalkResponse> result = dialogueService.getDiarogueTalks(user, DESIRE_HAVE_USER_ID_NAME, STERT_INDEX, MAX_SIZE);
			assertThat(result).containsExactlyElementsOf(expect);
		} catch (NotFoundException | NotHaveUserException e) {
			assertTrue(false);
		}
	}
	
	//getDiarogueTalks
	//正常 過去・ラストトークインデックスの更新無
	@Test
	@Transactional
	void T02_getDiarogueTalks_2() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "3";
		final Integer USER_ID = 1;
		final Integer MAX_SIZE = 1;
		final Integer STERT_INDEX = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<TalkResponse> expect = new ArrayList<>();
		var entity = new TalkResponse();
		entity.setUserIdName("1");
		entity.setUserName("1");
		entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		entity.setTalkIndex(1);
		entity.setContent(CONTENT_TEXT_FIREST + "1");
		expect.add(entity);
		
		//実行
		try {
			List<TalkResponse> result = dialogueService.getDiarogueTalks(user, DESIRE_HAVE_USER_ID_NAME, STERT_INDEX, MAX_SIZE);
			assertThat(result).containsExactlyElementsOf(expect);
		} catch (NotFoundException | NotHaveUserException e) {
			assertTrue(false);
		}
	}
	
	//getDiarogueTalks
	//正常 範囲外で0件の取得
	@Test
	@Transactional
	void T03_getDiarogueTalks_3() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "3";
		final Integer USER_ID = 1;
		final Integer MAX_SIZE = 3;
		final Integer STERT_INDEX = 2;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			List<TalkResponse> result = dialogueService.getDiarogueTalks(user, DESIRE_HAVE_USER_ID_NAME, STERT_INDEX, MAX_SIZE);
			assertThat(result).isEmpty();
		} catch (NotFoundException | NotHaveUserException e) {
			assertTrue(false);
		}
	}
	
	//getDiarogueTalks
	//正常 トークルームのラストトークインデックス以下で、ユーザーのラストトークインデックスが更新される
	@Test
	@Transactional
	void T04_getDiarogueTalks_4() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "70";
		final Integer USER_ID = 1;
		final Integer MAX_SIZE = 5;
		final Integer STERT_INDEX = 21;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<TalkResponse> expect = new ArrayList<>();
		for(Integer i = 21;i <= 25; i++) {
			var entity = new TalkResponse();
			entity.setUserIdName("70");
			entity.setUserName("1");
			entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
			entity.setTalkIndex(i);
			entity.setContent(CONTENT_TEXT_FIREST + i.toString());
			expect.add(entity);
		}
		
		//実行
		try {
			List<TalkResponse> result = dialogueService.getDiarogueTalks(user, DESIRE_HAVE_USER_ID_NAME, STERT_INDEX, MAX_SIZE);
			assertThat(result).containsExactlyElementsOf(expect);
		} catch (NotFoundException | NotHaveUserException e) {
			assertTrue(false);
		}
	}
	
	//getDiarogueTalks
	//正常 トークルームのラストトークインデックスを超えて、ユーザーのラストトークインデックスが更新される
	@Test
	@Transactional
	void T05_getDiarogueTalks_5() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "70";
		final Integer USER_ID = 1;
		final Integer MAX_SIZE = 20;
		final Integer STERT_INDEX = 21;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<TalkResponse> expect = new ArrayList<>();
		for(Integer i = 21;i <= 30; i++) {
			var entity = new TalkResponse();
			entity.setUserIdName("70");
			entity.setUserName("1");
			entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
			entity.setTalkIndex(i);
			entity.setContent(CONTENT_TEXT_FIREST + i.toString());
			expect.add(entity);
		}
		
		//実行
		try {
			List<TalkResponse> result = dialogueService.getDiarogueTalks(user, DESIRE_HAVE_USER_ID_NAME, STERT_INDEX, MAX_SIZE);
			assertThat(result).containsExactlyElementsOf(expect);
		} catch (NotFoundException | NotHaveUserException e) {
			assertTrue(false);
		}
	}
	
	//getDiarogueTalks
	//異常 加入してない
	@Test
	@Transactional
	void T06_getDiarogueTalks_6() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "70";
		final Integer USER_ID = 3;
		final Integer MAX_SIZE = 20;
		final Integer STERT_INDEX = 21;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotHaveUserException.class ,
				() -> dialogueService.getDiarogueTalks(user, DESIRE_HAVE_USER_ID_NAME, STERT_INDEX, MAX_SIZE));
	}
}