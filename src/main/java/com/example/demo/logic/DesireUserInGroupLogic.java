package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.DesireUserInGroupEntityExample;
import com.example.demo.entity.DesireUserInGroupEntity;
import com.example.demo.exception.AlreadyInsertedGroupDesireException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotInsertedGroupDesireException;
import com.example.demo.repository.DesireUserInGroupEntityMapper;

/**
 * グループ申請リスト周りの共通処理クラス
 */
@Component
public class DesireUserInGroupLogic {

	@Autowired
	DesireUserInGroupEntityMapper desireUserInGroupEntityMapper;
	
	/**
	 * グループに入ってほしい申請の削除
	 * @param userId ユーザーID
	 * @param talkRoomId グループトークルームID
	 */
	public void delete(Integer userId, Integer talkRoomId) {
		desireUserInGroupEntityMapper.deleteByPrimaryKey(userId, talkRoomId);
	}

	/**
	 * グループに入ってほしい申請の取得
	 * @param userId ユーザーID
	 * @param talkRoomId グループトークルームID
	 * @return グループに入ってほしい申請
	 * @throws NotFoundException 見つからないと投げる
	 */
	public DesireUserInGroupEntity getDesireGroup(Integer userId, Integer talkRoomId) throws NotFoundException {
		//データ取得
		var entity = desireUserInGroupEntityMapper.selectByPrimaryKey(userId, talkRoomId);
		
		//処理
		if(entity != null)
			return entity;
		else
			throw new NotFoundException("DesireUserInGroup");
	}
	
	/**
	 * グループに入ってほしい申請リストの取得
	 * @param userId ユーザーID
	 * @return グループに入ってほしい申請者リスト
	 */
	public List<DesireUserInGroupEntity> getDesireGroupList(Integer userId) {
		//SQL作成
		var dto = new DesireUserInGroupEntityExample();
		dto
			.or()
				.andUserIdEqualTo(userId);
		
		//処理
		return desireUserInGroupEntityMapper.selectByExample(dto);
	}

	/**
	 * グループ加入してほしい申請の追加
	 * @param talkRoomId グループトークルームID
	 * @param userId ユーザーID
	 * @param lastIndex グループトークルームの最後のトークインデックス
	 */
	public void insert(Integer talkRoomId, Integer userId, Integer lastIndex) {
		//データ作成
		var entity = new DesireUserInGroupEntity();
		entity.setTalkRoomId(talkRoomId);
		entity.setUserId(userId);
		entity.setLastTalkIndex(lastIndex);
		
		//処理
		desireUserInGroupEntityMapper.insert(entity);
	}

	/**
	 * グループに加入してほしい申請が出されているかのチェック
	 * @param talkRoomId グループトークルームID
	 * @param userId　ユーザーID
	 * @return 成功ならtrue、失敗ならfalse
	 */
	public boolean isInserted(Integer talkRoomId, Integer userId) {
		return desireUserInGroupEntityMapper.selectByPrimaryKey(userId, talkRoomId) != null;
	}
	
	/**
	 * グループに加入してほしい申請が出されているかのチェック
	 * @param talkRoomId グループトークルームID
	 * @param userId　ユーザーID
	 * @throws NotInsertedGroupDesireException 申請が出されてない
	 */
	public void validationInserted(Integer talkRoomId, Integer userId) throws NotInsertedGroupDesireException {
		if(!isInserted(talkRoomId, userId))
			throw new NotInsertedGroupDesireException();
	}
	
	/**
	 * ラストトークインデックスの更新
	 * @param userId ユーザーID
	 * @param talkRoomId グループトークルームID
	 * @param lastTalkIndex ラストトークインデックス
	 */
	public void updateLastTalkIndex(Integer userId, Integer talkRoomId, Integer lastTalkIndex) {
		//データ作成
		var entity = new DesireUserInGroupEntity();
		entity.setTalkRoomId(talkRoomId);
		entity.setUserId(userId);
		entity.setLastTalkIndex(lastTalkIndex);
		
		//SQL作成
		var dto = new DesireUserInGroupEntityExample();
		dto
			.or()
				.andUserIdEqualTo(userId)
				.andTalkRoomIdEqualTo(talkRoomId)
				.andLastTalkIndexLessThan(lastTalkIndex);
		
		//処理
		desireUserInGroupEntityMapper.updateByExample(entity, dto);
	}

	public void validationNotInserted(Integer talkRoomId, Integer userId) throws AlreadyInsertedGroupDesireException {
		if(isInserted(talkRoomId, userId))
			throw new AlreadyInsertedGroupDesireException();
	}
}