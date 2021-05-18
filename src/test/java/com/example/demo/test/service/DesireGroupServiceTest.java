package com.example.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.DesireGroupService;

@SpringBootTest
public class DesireGroupServiceTest {
	@Autowired
	DesireGroupService desireGroupService;
	
	/*
	 * @BeforeEach
	 * @Test
	 * @Transactional
	 */
	
	//getDesireGroup
	//正常1
	//正常2
	
	//deleteDesireGroup
	//正常
	//申請出てない
	//グループが存在しない
	
	//joinGroup
	//正常
	//異常 申請出てない
}
