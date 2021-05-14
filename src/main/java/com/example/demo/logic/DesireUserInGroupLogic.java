package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.DesireUserInGroupEntityExample;
import com.example.demo.entity.DesireUserInGroupEntity;
import com.example.demo.exception.NotFoundException;
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
	 * グループに入ってほしい申請が実際に存在するかのチェック<br>
	 * 失敗すると例外を投げる
	 * @param talkRoomId グループトークルームID
	 * @param userId ユーザーID
	 * @throws NotFoundException 見つからない
	 */
	public void validationIsFound(Integer talkRoomId, Integer userId) throws NotFoundException {
		if(!isFound(talkRoomId, userId))
			throw new NotFoundException("DesireUserInGroup");
	}

	/**
	 * グループに入ってほしい申請が実際に存在するかのチェック
	 * @param talkRoomId グループトークルームID
	 * @param userId ユーザーID
	 * @return 成功ならtrue、失敗ならfalse
	 */
	public boolean isFound(Integer talkRoomId, Integer userId) {
		return desireUserInGroupEntityMapper.selectByPrimaryKey(userId, talkRoomId) != null;
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
}