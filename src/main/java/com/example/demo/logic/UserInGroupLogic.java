package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UserInGroupEntityExample;
import com.example.demo.entity.DesireUserInGroupEntity;
import com.example.demo.entity.UserInGroupEntity;
import com.example.demo.exception.NotJoinGroupException;
import com.example.demo.repository.UserInGroupEntityMapper;

/**
 * グループ加入者周りの共通処理クラス
 */
@Component
public class UserInGroupLogic {

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

	/**
	 * グループに加入しているかのチェック
	 * @param talkRoomId グループトークルームId
	 * @param userId ユーザーID
	 * @throws NotJoinGroupException グループに加入してない
	 */
	public void validationJoinGroup(Integer talkRoomId, Integer userId) throws NotJoinGroupException {
		if(!isJoinGroup(talkRoomId, userId))
			throw new NotJoinGroupException();
	}

	/**
	 * グループに加入しているかのチェック
	 * @param talkRoomId グループトークルームId
	 * @param userId ユーザーID
	 * @return 成功ならtrue、失敗ならfalse
	 */
	public boolean isJoinGroup(Integer talkRoomId, Integer userId) {
		return userInGroupEntityMapper.selectByPrimaryKey(talkRoomId, userId) != null;
	}
	
	/**
	 * グループに加入しているユーザーリストを取得する
	 * @param talkRoomId グループトークルームID
	 * @return グループに加入しているユーザーリスト
	 */
	public List<UserInGroupEntity> getUserInGroupList(Integer talkRoomId) {
		var dto = new UserInGroupEntityExample();
		dto
			.or()
				.andTalkRoomIdEqualTo(talkRoomId);
		
		return userInGroupEntityMapper.selectByExample(dto);
	}

	/**
	 * グループに加入しているユーザーを削除する（脱退させる）
	 * @param talkRoomId グループトークルームID
	 * @param userId ユーザーID
	 */
	public void delete(Integer talkRoomId, Integer userId) {
		userInGroupEntityMapper.deleteByPrimaryKey(talkRoomId, userId);
	}

}