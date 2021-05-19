package com.example.demo.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.NotFoundException;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserDetailsServiceImp;

@SpringBootTest
class UserDetailsServiceImpTest {
	@Autowired
	UserDetailsServiceImp userDetailsServiceImp;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 * @ExpectedDatabase(
            value = "classpath:src/test/resources/expectations/UserDetailsServiceImpTest/T01.xlsx",
             assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	 */
	
	//loadUserByUserIdName
	//正常
	@Test
	@Transactional
	void T01_loadUserByUserIdName_1() {
		//テストパラメータ作成
		final String USER_ID_NAME = "2";
		
		//期待値作成
		final Integer USER_ID = 2;
		final String USER_NAME = "1";
		
		
		//実行
		UserDetailsImp result;
		try {
			result = userDetailsServiceImp.loadUserByUserIdName(USER_ID_NAME);
			assertThat(result.getUserId()).isEqualTo(USER_ID);
			assertThat(result.getUserIdName()).isEqualTo(USER_ID_NAME);
			assertThat(result.getUsername()).isEqualTo(USER_NAME);
		} catch (NotFoundException e) {
			assertTrue(false);
		}
	}
	
	//loadUserByUserIdName
	//異常 存在しない
	@Test
	@Transactional
	void T02_loadUserByUserIdName_2() {
		//テストパラメータ作成
		final String USER_ID_NAME = "8";
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> userDetailsServiceImp.loadUserByUserIdName(USER_ID_NAME));
	}
	
	//loadUserByUserIdName
	//異常 削除されてる
	@Test
	@Transactional
	void T03_loadUserByUserIdName_3() {
		//テストパラメータ作成
		final String USER_ID_NAME = "4";
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> userDetailsServiceImp.loadUserByUserIdName(USER_ID_NAME));
	}
	
	//loadUserByUserId
	//正常
	@Test
	@Transactional
	void T04_loadUserByUserId_1() {
		//テストパラメータ作成
		final Integer USER_ID = 2;
		
		//期待値作成
		final String USER_NAME = "1";
		
		//実行
		try {
			UserDetails result = userDetailsServiceImp.loadUserByUserId(USER_ID);
			assertThat(result.getUsername()).isEqualTo(USER_NAME);
		} catch (NotFoundException e) {
			assertTrue(false);
		}
	}
	
	//loadUserByUserId
	//異常　存在しない
	@Test
	@Transactional
	void T05_loadUserByUserId_2() {
		//テストパラメータ作成
		final Integer USER_ID = 8;
		
		//実行
		assertThrows(NotFoundException.class ,
				() -> userDetailsServiceImp.loadUserByUserId(USER_ID));
	}
}
