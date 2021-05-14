package com.example.demo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.DesireUserInGroupEntity;
import com.example.demo.entity.UserInGroupEntity;
import com.example.demo.repository.UserInGroupEntityMapper;

/**
 * グループ加入者周りの共通処理クラス
 */
@Component
public class UserInGroupLodic {

	@Autowired
	UserInGroupEntityMapper userInGroupEntityMapper;
	
	/**
	 * グループに加入してほしい申請を出されたユーザーがグループに入る
	 * @param desireEntity グループに加入してほしい申請
	 */
	public void joinGroup(DesireUserInGroupEntity desireEntity) {
		//データセット
		var entity = new UserInGroupEntity();
		entity.setTalkRoomId(
				desireEntity.getTalkRoomId());
		entity.setUserId(
				desireEntity.getUserId());
		entity.setLastTalkIndex(
				desireEntity.getLastTalkIndex());
		
		//処理
		userInGroupEntityMapper.insert(entity);
	}

}