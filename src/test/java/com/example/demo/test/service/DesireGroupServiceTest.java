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

import com.example.demo.entity.response.DesireUserInGroupResponce;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotInsertedGroupDesireException;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.DesireGroupService;

@SpringBootTest
class DesireGroupServiceTest {
	@Autowired
	DesireGroupService desireGroupService;
	
	//getDesireGroup
	//正常1
	@Test
	@Transactional
	void T01_getDesireGroup_1() {
		//テストパラメータ作成
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<DesireUserInGroupResponce> expect = new ArrayList<>();
		var entity = new DesireUserInGroupResponce();
		entity.setTalkRoomId(7);
		entity.setGroupName("test_group4");
		entity.setLastTalkIndex(4);
		expect.add(entity);
		
		//実行
		List<DesireUserInGroupResponce> result = desireGroupService.getDesireGroupList(user);
		assertThat(result).containsExactlyElementsOf(expect);
	}
	
	//getDesireGroup
	//正常2
	@Test
	@Transactional
	void T02_getDesireGroup_2() {
		//テストパラメータ作成
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<DesireUserInGroupResponce> expect = new ArrayList<>();
		var entity = new DesireUserInGroupResponce();
		entity.setTalkRoomId(6);
		entity.setGroupName("test_group3");
		entity.setLastTalkIndex(1);
		expect.add(entity);
		
		//実行
		List<DesireUserInGroupResponce> result = desireGroupService.getDesireGroupList(user);
		assertThat(result).containsExactlyElementsOf(expect);
	}
	
	//deleteDesireGroup
	//正常
	@Test
	@Transactional
	void T03_deleteDesireGroup_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 7;
		final Integer USER_ID = 2;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			desireGroupService.deleteDesireGroup(user, GROUP_TALK_ROOM_ID);
			assertTrue(true);
		} catch (NotFoundException | NotInsertedGroupDesireException e) {
			assertTrue(false);
		}
	}
	
	//deleteDesireGroup
	//申請出てない
	@Test
	@Transactional
	void T04_deleteDesireGroup_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 6;
		final Integer USER_ID = 2;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotInsertedGroupDesireException.class ,
				() -> desireGroupService.deleteDesireGroup(user, GROUP_TALK_ROOM_ID));
	}
	
	//deleteDesireGroup
	//グループが存在しない
	@Test
	@Transactional
	void T05_deleteDesireGroup_3() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 3;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> desireGroupService.deleteDesireGroup(user, GROUP_TALK_ROOM_ID));
	}
	
	//deleteDesireGroup
	//グループが削除されてる
	@Test
	@Transactional
	void T08_deleteDesireGroup_4() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 4;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> desireGroupService.deleteDesireGroup(user, GROUP_TALK_ROOM_ID));
	}
	
	//joinGroup
	//正常
	@Test
	@Transactional
	void T06_joinGroup_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 6;
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			desireGroupService.joinGroup(user, GROUP_TALK_ROOM_ID);
			assertTrue(true);
		} catch (NotFoundException | NotInsertedGroupDesireException e) {
			assertTrue(false);
		}
	}
	
	//joinGroup
	//異常 申請出てない
	@Test
	@Transactional
	void T07_joinGroup_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final Integer USER_ID = 70;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotInsertedGroupDesireException.class ,
				() -> desireGroupService.joinGroup(user, GROUP_TALK_ROOM_ID));
	}
	
	//getDesireGroup
	//正常
	@Test
	@Transactional
	void T08_getDesireGroup_1() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 7;
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		var expect = new DesireUserInGroupResponce();
		expect.setTalkRoomId(GROUP_TALK_ROOM_ID);
		expect.setGroupName("test_group4");
		expect.setLastTalkIndex(4);
		
		try {
			DesireUserInGroupResponce result = desireGroupService.getDesireGroup(user, GROUP_TALK_ROOM_ID);
			assertThat(result).isEqualTo(expect);
		} catch (NotFoundException e) {
			assertTrue(false);
		}
	}
	
	//getDesireGroup
	//異常 申請出てない
	@Test
	@Transactional
	void T09_getDesireGroup_2() {
		//テストパラメータ作成
		final Integer GROUP_TALK_ROOM_ID = 5;
		final Integer USER_ID = 70;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> desireGroupService.getDesireGroup(user, GROUP_TALK_ROOM_ID));
	}
}
