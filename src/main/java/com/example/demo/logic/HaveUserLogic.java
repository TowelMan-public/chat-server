package com.example.demo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.DesireHaveUserEntity;
import com.example.demo.entity.HaveUserEntity;
import com.example.demo.repository.HaveUserEntityMapper;

/**
 * 友達周りの共通処理クラス
 */
@Component
public class HaveUserLogic {

	@Autowired
	HaveUserEntityMapper haveUserEntityMapper;
	
	/**
	 * 友達追加を受ける（友達追加をする）
	 * @param desireEntity 友達追加申請
	 */
	public void join(DesireHaveUserEntity desireEntity) {
		//データ作成
		var entity = new HaveUserEntity();
		entity.setUserId(
				desireEntity.getUserId());
		entity.setHaveUserId(
				desireEntity.getHaveUserId());
		entity.setTalkRoomId(
				desireEntity.getTalkRoomId());
		entity.setLastTalkIndex(
				desireEntity.getLastTalkIndex());
		
		haveUserEntityMapper.insertSelective(entity);
	}
}