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
import com.example.demo.exception.NotHaveUserException;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.DialogueTalkService;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
class DialogueTalkServiceTest {
	@Autowired
	DialogueTalkService dialogueTalkService;
	
	
	private static final String TIMESTAMP_STRING_TEMPLATE = "2020/9/9/00/00";
	
	private static final String CONTENT_TEXT_FIREST = "test";
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 * @ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/DialogueTalkServiceTest/T01.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	 */
	
	//insertTalk
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/DialogueTalkServiceTest/T01.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T01_insertTalk_1() {
		//テストパラメータ作成
		final String HAVE_USER_ID_NAME = "1";
		final String TALK_CONTENT_TEXT = "test Hey! you!!";
		final Integer USER_ID = 2;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			dialogueTalkService.insertTalk(user, HAVE_USER_ID_NAME, TALK_CONTENT_TEXT);
			assertTrue(true);
		} catch (NotFoundException | NotHaveUserException e) {
			assertTrue(false);
		}
	}
	
	//insertTalk
	//異常　友達登録してない
	@Test
	@Transactional
	void T02_insertTalk_2() {
		//テストパラメータ作成
		final String HAVE_USER_ID_NAME = "70";
		final String TALK_CONTENT_TEXT = "test Hey! you!!";
		final Integer USER_ID = 2;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> dialogueTalkService.insertTalk(user, HAVE_USER_ID_NAME, TALK_CONTENT_TEXT));
	}
	
	//getTalk
	//正常
	@Test
	@Transactional
	void T03_getTalk_1() {
		//テストパラメータ作成
		final Integer TALK_INDEX = 1;
		final String HAVE_USER_ID_NAME = "70";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		var expect = new TalkResponse();
		expect.setUserIdName("1");
		expect.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		expect.setTalkIndex(1);
		expect.setContent(CONTENT_TEXT_FIREST + "1");
		
		//実行
		try {
			TalkResponse result = dialogueTalkService.getTalk(user, HAVE_USER_ID_NAME, TALK_INDEX);
			assertThat(result).isEqualTo(expect);
			
		} catch (NotFoundException | NotHaveUserException e) {
			assertTrue(false);
		}
	}
	
	//getTalk
	//異常 存在しないトーク
	@Test
	@Transactional
	void T04_getTalk_2() {
		//テストパラメータ作成
		final Integer TALK_INDEX = 5;
		final String HAVE_USER_ID_NAME = "2";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> dialogueTalkService.getTalk(user, HAVE_USER_ID_NAME, TALK_INDEX));
	}
	
	//updateTalk
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/DialogueTalkServiceTest/T05.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T05_updateTalk_1() {
		//テストパラメータ作成
		final String TALK_CONTENT_TEXT = "I like Visual Studio but now I have using Eclipse for about half month for making about web apps.";
		final Integer TALK_INDEX = 1;
		final String HAVE_USER_ID_NAME = "3";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			dialogueTalkService.updateTalk(user, HAVE_USER_ID_NAME, TALK_INDEX, TALK_CONTENT_TEXT);
			assertTrue(true);
		} catch (NotFoundException | NotHaveUserException | BadRequestFormException e) {
			assertTrue(false);
		}
	}
	
	//updateTalk
	//異常 違うユーザーが作ったトーク
	@Test
	@Transactional
	void T06_updateTalk_2() {
		//テストパラメータ作成
		final String TALK_CONTENT_TEXT = "I like Visual Studio but now I have using Eclipse for about half month for making about web apps.";
		final Integer TALK_INDEX = 1;
		final String HAVE_USER_ID_NAME = "1";
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(BadRequestFormException.class ,
				() -> dialogueTalkService.updateTalk(user, HAVE_USER_ID_NAME, TALK_INDEX, TALK_CONTENT_TEXT));
	}
	
	//deleteTalk
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/DialogueTalkServiceTest/T07.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T07_deleteTalk_1() {
		//テストパラメータ作成
		final Integer TALK_INDEX = 1;
		final String HAVE_USER_ID_NAME = "3";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			dialogueTalkService.deleteTalk(user, HAVE_USER_ID_NAME, TALK_INDEX);
			assertTrue(true);
		} catch (NotFoundException | NotHaveUserException | BadRequestFormException e) {
			assertTrue(false);
		}
	}
	
	//deleteTalk
	//異常 違うユーザーが作ったトーク
	@Test
	@Transactional
	void T08_deleteTalk_2() {
		//テストパラメータ作成
		final Integer TALK_INDEX = 1;
		final String HAVE_USER_ID_NAME = "1";
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(BadRequestFormException.class ,
				() -> dialogueTalkService.deleteTalk(user, HAVE_USER_ID_NAME, TALK_INDEX));
	}
}