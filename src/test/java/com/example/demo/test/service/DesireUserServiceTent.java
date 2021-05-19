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
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
class DesireUserServiceTest {
	@Autowired
	DesireUserService desireUserService;
	
	/*
	 * expect
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 * @ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/DesireUserServiceTest/T01.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	 */
	
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
		List<DesireHaveUserResponse> result = desireUserService.getDesireUser(user);
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
		entity.setLastTalkIndex(1);
		expect.add(entity);
		
		//実行
		List<DesireHaveUserResponse> result = desireUserService.getDesireUser(user);
		assertThat(result).containsExactlyElementsOf(expect);
	}
	
	//deleteDesireUser
	//正常
	@Test
	@Transactional
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/DesireUserServiceTest/T03.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
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
	@ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/DesireUserServiceTest/T06.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
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
}
