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

import com.example.demo.entity.response.DesireHaveUserResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.DesireUserService;

@SpringBootTest
class DesireUserServiceTest {
	@Autowired
	DesireUserService desireUserService;
	
	//getDesireUser
	//正常1
	@Test
	@Transactional
	void T01_getDesireUser_1() {
		//テストパラメータ作成
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		List<DesireHaveUserResponse> result = desireUserService.getDesireUserList(user);
		assertThat(result).isEmpty();
	}
	
	//getDesireUser
	//正常2
	@Test
	@Transactional
	void T02_getDesireUser_2() {
		//テストパラメータ作成
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値作成
		List<DesireHaveUserResponse> expect = new ArrayList<>();
		var entity = new DesireHaveUserResponse();
		entity.setHaveUserIdName("1");
		entity.setHaveUserName("1");
		entity.setTalkRoomId(3);
		entity.setLastTalkIndex(0);
		expect.add(entity);
		
		//実行
		List<DesireHaveUserResponse> result = desireUserService.getDesireUserList(user);
		assertThat(result).containsExactlyElementsOf(expect);
	}
	
	//deleteDesireUser
	//正常
	@Test
	@Transactional
	void T03_deleteDesireUser_1() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "1";
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			desireUserService.deleteDesireUser(user, DESIRE_HAVE_USER_ID_NAME);
			assertTrue(true);
		} catch (NotFoundException e) {
			assertTrue(false);
		}
	}
	
	//deleteDesireUser
	//異常　ユーザー見つからない
	@Test
	@Transactional
	void T04_deleteDesireUser_2() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "nothing";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> desireUserService.deleteDesireUser(user, DESIRE_HAVE_USER_ID_NAME));
	}
	
	//deleteDesireUser
	//異常 申請出してない
	@Test
	@Transactional
	void T05_deleteDesireUser_3() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "3";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> desireUserService.deleteDesireUser(user, DESIRE_HAVE_USER_ID_NAME));
	}
	
	//joinUser
	//正常
	@Test
	@Transactional
	void T06_joinUser_1() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "1";
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		try {
			desireUserService.joinUser(user, DESIRE_HAVE_USER_ID_NAME);
			assertTrue(true);
		} catch (NotFoundException e) {
			assertTrue(false);
		}
	}
	
	//joinUser
	//異常 申請出てない
	@Test
	@Transactional
	void T06_joinUser_2() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "2";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> desireUserService.joinUser(user, DESIRE_HAVE_USER_ID_NAME));
	}
	
	//joinUser
	//異常 ユーザー見つからない（元からない）
	@Test
	@Transactional
	void T07_joinUser_3() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "nothing";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> desireUserService.joinUser(user, DESIRE_HAVE_USER_ID_NAME));
	}
	
	//getDesireUser
	//正常
	@Test
	@Transactional
	void T08_getDesireUser_1() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "1";
		final Integer USER_ID = 3;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//期待値
		var expect = new DesireHaveUserResponse();
		expect.setHaveUserIdName(DESIRE_HAVE_USER_ID_NAME);
		expect.setHaveUserName("1");
		expect.setTalkRoomId(3);
		expect.setLastTalkIndex(0);
		
		try {
			DesireHaveUserResponse result = desireUserService.getDesireUser(user, DESIRE_HAVE_USER_ID_NAME);
			assertThat(result).isEqualTo(expect);
		} catch (NotFoundException e) {
			assertTrue(false);
		}
	}
	
	//getDesireUser
	//異常 ユーザー見つからない（元からない）
	@Test
	@Transactional
	void T09_getDesireUser_2() {
		//テストパラメータ作成
		final String DESIRE_HAVE_USER_ID_NAME = "nothing";
		final Integer USER_ID = 1;
		var user = new UserDetailsImp();
		user.setUserId(USER_ID);
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> desireUserService.getDesireUser(user, DESIRE_HAVE_USER_ID_NAME));
	}
}
