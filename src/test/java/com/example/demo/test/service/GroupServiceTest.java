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

import com.example.demo.entity.response.GroupTalkRoomResponse;
import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotJoinGroupException;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.GroupService;

@SpringBootTest
class GroupServiceTest {
	@Autowired
	GroupService groupService;
	
	private static final String TIMESTAMP_STRING_TEMPLATE = "2020/9/9  00:00";
	
	private static final String CONTENT_TEXT_FIREST = "test";
	
	//getGroup
	//正常
	@Test
	@Transactional
	void T01_getGroup_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		var expect = new GroupTalkRoomResponse();
		expect.setTalkRoomId(5);
		expect.setGroupName("test_group1");
		expect.setUserLastTalkIndex(2);
		expect.setGroupLastTalkIndex(7);
		
		//実行
		try {
			GroupTalkRoomResponse result = groupService.getGroup(user, GROUP_TALK_ROOM_ID);
			assertThat(result).isEqualTo(expect);
		} catch (NotJoinGroupException | NotFoundException e) {
			assertTrue(false);
		}
	}
	
	//getGroup
	//異常　存在しないグループ
	@Test
	@Transactional
	void T02_getGroup_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 10;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> groupService.getGroup(user, GROUP_TALK_ROOM_ID));
	}
	
	//getGroups
	//正常
	@Test
	@Transactional
	void T03_getGroups_1() {
		//テストパラメータ作成
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		List<GroupTalkRoomResponse> expect = new ArrayList<>();
		var entity = new GroupTalkRoomResponse();
		entity.setTalkRoomId(5);
		entity.setGroupName("test_group1");
		entity.setGroupLastTalkIndex(7);
		entity.setUserLastTalkIndex(2);
		expect.add(entity);
		
		entity = new GroupTalkRoomResponse();
		entity.setTalkRoomId(7);
		entity.setGroupName("test_group4");
		entity.setGroupLastTalkIndex(5);
		entity.setUserLastTalkIndex(5);
		expect.add(entity);
		
		
		//実行
		List<GroupTalkRoomResponse> result = groupService.getGroups(user);
		assertThat(result).containsExactlyElementsOf(expect);
	}
	
	//updateGroupName
	//正常
	@Test
	@Transactional
	void T04_updateGroupName_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 6;
		final Integer USER_ID = 3;
		final String GROUP_NAME = "changed_group_name";
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			groupService.updateGroupName(user, GROUP_TALK_ROOM_ID, GROUP_NAME);
			assertTrue(true);
		} catch (NotJoinGroupException | NotFoundException e) {
			assertTrue(false);
		}
	}
	
	//updateGroupName
	//異常 グループ無
	@Test
	@Transactional
	void T05_updateGroupName_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 10;
		final Integer USER_ID = 3;
		final String GROUP_NAME = "changed_group_name";
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> groupService.updateGroupName(user, GROUP_TALK_ROOM_ID, GROUP_NAME));
	}
	
	//getGroupTalks
	//正常　申請されているユーザー
	@Test
	@Transactional
	void T07_getGroupTalks_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 6;
		final Integer USER_ID = 1;
		final Integer START_INDEX = 1;
		final Integer MAX_SIZE = 4;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<TalkResponse> expect = new ArrayList<>();
		var entity = new TalkResponse();
		entity.setUserIdName("3");
		entity.setUserName("1");
		entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		entity.setTalkIndex(1);
		entity.setContent(CONTENT_TEXT_FIREST + "1");
		expect.add(entity);
		
		entity = new TalkResponse();
		entity.setUserIdName("3");
		entity.setUserName("1");
		entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		entity.setTalkIndex(2);
		entity.setContent(CONTENT_TEXT_FIREST + "2");
		expect.add(entity);
		
		//実行
		try {
			List<TalkResponse> result = groupService.getGroupTalks(user, GROUP_TALK_ROOM_ID, START_INDEX, MAX_SIZE);
			assertThat(result).containsExactlyElementsOf(expect);
		} catch (NotFoundException | NotJoinGroupException e) {
			assertTrue(false);
		}
	}	
	
	//getGroupTalks
	//正常 過去・ラストトークインデックスの更新無
	@Test
	@Transactional
	void T08_getGroupTalks_3() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 6;
		final Integer USER_ID = 3;
		final Integer START_INDEX = 2;
		final Integer MAX_SIZE = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<TalkResponse> expect = new ArrayList<>();
		var entity = new TalkResponse();
		entity.setUserIdName("3");
		entity.setUserName("1");
		entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		entity.setTalkIndex(2);
		entity.setContent(CONTENT_TEXT_FIREST + "2");
		expect.add(entity);
		
		//実行
		try {
			List<TalkResponse> result = groupService.getGroupTalks(user, GROUP_TALK_ROOM_ID, START_INDEX, MAX_SIZE);
			assertThat(result).containsExactlyElementsOf(expect);
		} catch (NotFoundException | NotJoinGroupException e) {
			assertTrue(false);
		}
	}
	
	//getGroupTalks
	//正常 範囲外で0件の取得
	@Test
	@Transactional
	void T09_getGroupTalks_4() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 6;
		final Integer USER_ID = 3;
		final Integer START_INDEX = 20;
		final Integer MAX_SIZE = 30;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			List<TalkResponse> result = groupService.getGroupTalks(user, GROUP_TALK_ROOM_ID, START_INDEX, MAX_SIZE);
			assertThat(result).isEmpty();
		} catch (NotFoundException | NotJoinGroupException e) {
			assertTrue(false);
		}
	}
	
	//getGroupTalks
	//正常 トークルームのラストトークインデックス以下で、ユーザーのラストトークインデックスが更新される
	@Test
	@Transactional
	void T10_getGroupTalks_5() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final Integer USER_ID = 1;
		final Integer START_INDEX = 3;
		final Integer MAX_SIZE = 2;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<TalkResponse> expect = new ArrayList<>();
		var entity = new TalkResponse();
		entity.setUserIdName("3");
		entity.setUserName("1");
		entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		entity.setTalkIndex(4);
		entity.setContent(CONTENT_TEXT_FIREST + "4");
		expect.add(entity);
		
		//実行
		try {
			List<TalkResponse> result = groupService.getGroupTalks(user, GROUP_TALK_ROOM_ID, START_INDEX, MAX_SIZE);
			assertThat(result).containsExactlyElementsOf(expect);
		} catch (NotFoundException | NotJoinGroupException e) {
			assertTrue(false);
		}
	}
	
	//getGroupTalks
	//正常 トークルームのラストトークインデックスを超えて、ユーザーのラストトークインデックスが更新される
	@Test
	@Transactional
	void T11_getGroupTalks_6() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final Integer USER_ID = 1;
		final Integer START_INDEX = 3;
		final Integer MAX_SIZE = 40;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<TalkResponse> expect = new ArrayList<>();
		var entity = new TalkResponse();
		entity.setUserIdName("3");
		entity.setUserName("1");
		entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		entity.setTalkIndex(4);
		entity.setContent(CONTENT_TEXT_FIREST + "4");
		expect.add(entity);
		
		entity = new TalkResponse();
		entity.setUserIdName("3");
		entity.setUserName("1");
		entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		entity.setTalkIndex(5);
		entity.setContent(CONTENT_TEXT_FIREST + "5");
		expect.add(entity);
		
		entity = new TalkResponse();
		entity.setUserIdName("3");
		entity.setUserName("1");
		entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		entity.setTalkIndex(6);
		entity.setContent(CONTENT_TEXT_FIREST + "6");
		expect.add(entity);
		
		entity = new TalkResponse();
		entity.setUserIdName("3");
		entity.setUserName("1");
		entity.setTimestampString(TIMESTAMP_STRING_TEMPLATE);
		entity.setTalkIndex(7);
		entity.setContent(CONTENT_TEXT_FIREST + "7");
		expect.add(entity);
		
		//実行
		try {
			List<TalkResponse> result = groupService.getGroupTalks(user, GROUP_TALK_ROOM_ID, START_INDEX, MAX_SIZE);
			assertThat(result).containsExactlyElementsOf(expect);
		} catch (NotFoundException | NotJoinGroupException e) {
			assertTrue(false);
		}
		
	}
	
	//getGroupTalks
	//異常　存在しないグループ 削除されてる
	@Test
	@Transactional
	void T12_getGroupTalks_7() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 4;
		final Integer USER_ID = 1;
		final Integer START_INDEX = 3;
		final Integer MAX_SIZE = 40;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> groupService.getGroupTalks(user, GROUP_TALK_ROOM_ID, START_INDEX, MAX_SIZE));
	}
	
	//deleteGroup
	//正常
	@Test
	@Transactional
	void T13_deleteGroup_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 6;
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			groupService.deleteGroup(user, GROUP_TALK_ROOM_ID);
			assertTrue(true);
		} catch (NotJoinGroupException | NotFoundException e) {
			assertTrue(false);
		}
	}
	
	//deleteGroup
	//異常　削除されたグループ
	@Test
	@Transactional
	void T14_deleteGroup_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 4;
		final Integer USER_ID = 2;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> groupService.deleteGroup(user, GROUP_TALK_ROOM_ID));
	}
	
	//insertGroup
	//成功
	@Test
	@Transactional
	void T15_insertGroup_1() {
		//テストパラメータ作成
		final String GROUP_NAME = "group_created_by_tester";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		groupService.insertGroup(user, GROUP_NAME);
		assertTrue(true);
	}
}