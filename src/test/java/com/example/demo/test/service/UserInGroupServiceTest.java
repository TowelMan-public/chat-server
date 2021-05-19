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

import com.example.demo.entity.response.UserInGroupResponse;
import com.example.demo.exception.AlreadyInsertedGroupDesireException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotJoinGroupException;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserInGroupService;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
class UserInGroupServiceTest {
	@Autowired
	UserInGroupService userInGroupService;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 * @ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/UserInGroupServiceTest/T01.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	 */
	
	//insertUserInGroup
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/UserInGroupServiceTest/T01.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T01_insertUserInGroup_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final Integer USER_ID = 1;
		final String USER_ID_NAME_INSERTED = "2";
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			userInGroupService.insertUserInGroup(user, GROUP_TALK_ROOM_ID, USER_ID_NAME_INSERTED);
			assertTrue(true);
		} catch (NotFoundException | NotJoinGroupException | AlreadyInsertedGroupDesireException e) {
			assertTrue(false);
		}
	}
	
	//insertUserInGroup
	//異常 存在しないグループ
	@Test
	@Transactional
	void T02_insertUserInGroup_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 8;
		final Integer USER_ID = 1;
		final String USER_ID_NAME_INSERTED = "2";
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> userInGroupService.insertUserInGroup(user, GROUP_TALK_ROOM_ID, USER_ID_NAME_INSERTED));
	}
	
	//insertUserInGroup
	//異常　すでに加入してるユーザーが指定された
	@Test
	@Transactional
	void T03_insertUserInGroup_3() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final Integer USER_ID = 1;
		final String USER_ID_NAME_INSERTED = "1";
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(AlreadyInsertedGroupDesireException.class ,
				() -> userInGroupService.insertUserInGroup(user, GROUP_TALK_ROOM_ID, USER_ID_NAME_INSERTED));
	}
	
	//insertUserInGroup
	//異常 既に申請が出ている
	@Test
	@Transactional
	void T04_insertUserInGroup_4() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 6;
		final Integer USER_ID = 1;
		final String USER_ID_NAME_INSERTED = "1";
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(AlreadyInsertedGroupDesireException.class ,
				() -> userInGroupService.insertUserInGroup(user, GROUP_TALK_ROOM_ID, USER_ID_NAME_INSERTED));
	}
	
	//insertUserInGroup
	//異常 グループに入ってない人がやろうしてる
	@Test
	@Transactional
	void T11_insertUserInGroup_5() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 4;
		final Integer USER_ID = 3;
		final String USER_ID_NAME_INSERTED = "4";
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotJoinGroupException.class ,
				() -> userInGroupService.insertUserInGroup(user, GROUP_TALK_ROOM_ID, USER_ID_NAME_INSERTED));
	}
	
	//getUsersInGroup
	//正常
	@Test
	@Transactional
	void T05_getUsersInGroup_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<UserInGroupResponse> expect = new ArrayList<>();
		var entity = new UserInGroupResponse();
		entity.setTalkRoomId(GROUP_TALK_ROOM_ID);
		entity.setUserIdName("1");
		entity.setLastTalkIndex(2);
		expect.add(entity);
		
		entity = new UserInGroupResponse();
		entity.setTalkRoomId(GROUP_TALK_ROOM_ID);
		entity.setUserIdName("4");
		entity.setLastTalkIndex(5);
		expect.add(entity);
		
		entity = new UserInGroupResponse();
		entity.setTalkRoomId(GROUP_TALK_ROOM_ID);
		entity.setUserIdName("3");
		entity.setLastTalkIndex(7);
		expect.add(entity);
		
		//実行
		List<UserInGroupResponse> result;
		try {
			result = userInGroupService.getUsersInGroup(user, GROUP_TALK_ROOM_ID);
			assertThat(result).containsExactlyElementsOf(expect);
		} catch (NotJoinGroupException e) {
			assertTrue(false);
		}		
	}
	
	//getUsersInGroup
	//異常　存在しないグループ
	@Test
	@Transactional
	void T06_getUsersInGroup_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 9;
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> userInGroupService.getUsersInGroup(user, GROUP_TALK_ROOM_ID));
	}
	
	//deleteUserInGroup
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/UserInGroupServiceTest/T07.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T07_deleteUserInGroup_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final Integer USER_ID = 1;
		final String USER_ID_NAME_INSERTED = "3";
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			userInGroupService.deleteUserInGroup(user, GROUP_TALK_ROOM_ID, USER_ID_NAME_INSERTED);
			assertTrue(true);
		} catch (NotFoundException | NotJoinGroupException e) {
			assertTrue(false);
		}
	}
	
	//deleteUserInGroup
	//異常　加入してないユーザー
	@Test
	@Transactional
	void T08_deleteUserInGroup_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 7;
		final Integer USER_ID = 1;
		final String USER_ID_NAME_INSERTED = "2";
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> userInGroupService.deleteUserInGroup(user, GROUP_TALK_ROOM_ID, USER_ID_NAME_INSERTED));
	}
	
	//exitGroup
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/UserInGroupServiceTest/T09.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void T09_exitGroup_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 7;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			userInGroupService.exitGroup(user, GROUP_TALK_ROOM_ID);
			assertTrue(true);
		} catch (NotJoinGroupException e) {
			assertTrue(false);
		}
	}
	
	//exitGroup
	//異常 存在しないグループ
	@Test
	@Transactional
	void T10_exitGroup_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 4;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotJoinGroupException.class ,
				() -> userInGroupService.exitGroup(user, GROUP_TALK_ROOM_ID));
	}
}
