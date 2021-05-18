package com.example.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserInDialogueServiceTest {
	@Autowired
	UserInDialogueServiceTest userInDialogueServiceTest;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 */
	
	//getUserInDiarogueList
	//正常 削除されているのが含まれる
	//正常 複数
	
	//insertUserInDiarogue
	//正常 相手も友達登録してない
	//正常 相手は友達登録してる
	//異常　既にユーザー登録している
	
	//deleteUserInDiarogue
	//正常 
	//異常 友達登録してない
}
