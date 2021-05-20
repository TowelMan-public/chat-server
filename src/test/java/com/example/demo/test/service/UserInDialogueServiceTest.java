package com.example.demo.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.response.HaveUserResponse;
import com.example.demo.exception.AlreadyHaveUserException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotHaveUserException;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserInDialogueService;

@SpringBootTest
class UserInDialogueServiceTest {
	@Autowired
	UserInDialogueService userInDialogueService;

	//getUserInDiarogueList
	//正常 削除されているのが含まれる
	@Test
	@Transactional
	void T01_getUserInDiarogueList_1() {
		//テストパラメータ作成
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<HaveUserResponse> expect = new ArrayList<>();
		var entity = new HaveUserResponse();
		entity.setHaveUserIdName("2");
		entity.setHaveUserName("1");
		entity.setMyLastTalkIndex(0);
		entity.setTalkLastTalkIndex(0);
		entity.setTalkRoomId(1);
		expect.add(entity);
		
		entity = new HaveUserResponse();
		entity.setHaveUserIdName("3");
		entity.setHaveUserName("1");
		entity.setMyLastTalkIndex(1);
		entity.setTalkLastTalkIndex(1);
		entity.setTalkRoomId(3);
		expect.add(entity);
		
		entity = new HaveUserResponse();
		entity.setHaveUserIdName("70");
		entity.setHaveUserName("1");
		entity.setMyLastTalkIndex(20);
		entity.setTalkLastTalkIndex(30);
		entity.setTalkRoomId(70);
		expect.add(entity);
		
		//実行
		List<HaveUserResponse> result = userInDialogueService.getUserInDiarogueList(user);
		assertThat(result).containsExactlyElementsOf(expect);
	}
	
	//getUserInDiarogueList
	//正常
	@Test
	@Transactional
	void T02_getUserInDiarogueList_2() {
		//テストパラメータ作成
		final Integer USER_ID = 2;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<HaveUserResponse> expect = new ArrayList<>();
		var entity = new HaveUserResponse();
		entity.setHaveUserIdName("1");
		entity.setHaveUserName("1");
		entity.setMyLastTalkIndex(0);
		entity.setTalkLastTalkIndex(0);
		entity.setTalkRoomId(1);
		expect.add(entity);
		
		//実行
		List<HaveUserResponse> result = userInDialogueService.getUserInDiarogueList(user);
		assertThat(result).containsExactlyElementsOf(expect);
	}
	
	//insertUserInDiarogue
	//正常 相手も友達登録してない
	@Test
	@Transactional
	void T03_insertUserInDiarogue_1() {
		//テストパラメータ作成
		final String HAVE_USER_ID_NAME = "2";
		final Integer USER_ID = 70;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			userInDialogueService.insertUserInDiarogue(user, HAVE_USER_ID_NAME);
			Assertions.assertTrue(true);
		} catch (NotFoundException | AlreadyHaveUserException e) {
			Assertions.assertTrue(false);
		}
	}
	
	//insertUserInDiarogue
	//正常 相手は友達登録してる
	@Test
	@Transactional
	void T04_insertUserInDiarogue_2() {
		//テストパラメータ作成
		final String HAVE_USER_ID_NAME = "1";
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			userInDialogueService.insertUserInDiarogue(user, HAVE_USER_ID_NAME);
			Assertions.assertTrue(true);
		} catch (NotFoundException | AlreadyHaveUserException e) {
			Assertions.assertTrue(false);
		}
	}
	
	//insertUserInDiarogue
	//異常　既にユーザー登録している
	@Test
	@Transactional
	void T05_insertUserInDiarogue_3() {
		//テストパラメータ作成
		final String HAVE_USER_ID_NAME = "3";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(AlreadyHaveUserException.class ,
				() -> userInDialogueService.insertUserInDiarogue(user, HAVE_USER_ID_NAME));
	}
	
	//deleteUserInDiarogue
	//正常
	@Test
	@Transactional
	void T06_deleteUserInDiarogue_1() {
		//テストパラメータ作成
		final String HAVE_USER_ID_NAME = "3";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			userInDialogueService.deleteUserInDiarogue(user, HAVE_USER_ID_NAME);
			Assertions.assertTrue(true);
		} catch (NotFoundException | NotHaveUserException e) {
			Assertions.assertTrue(false);
		}
	}
	
	//deleteUserInDiarogue
	//異常 友達登録してない
	@Test
	@Transactional
	void T07_deleteUserInDiarogue_2() {
		//テストパラメータ作成
		final String HAVE_USER_ID_NAME = "3";
		final Integer USER_ID = 70;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotHaveUserException.class ,
				() -> userInDialogueService.deleteUserInDiarogue(user, HAVE_USER_ID_NAME));
	}
}
