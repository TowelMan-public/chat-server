package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.DesireHaveUserEntityExample;
import com.example.demo.entity.DesireHaveUserEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.DesireHaveUserEntityMapper;

/**
 * ユーザー追加申請周りの共通処理クラス
 */
@Component
public class DesireUserLogic {

	@Autowired
	DesireHaveUserEntityMapper desireHaveUserEntityMapper;
	
	/**
	 * 友達追加申請を断る（友達追加申請を削除する）
	 * @param userId 友達追加申請を受けてるユーザーID
	 * @param haveUserId 友達追加申請をしてるユーザーID
	 */
	public void delete(Integer userId, Integer haveUserId) {
		desireHaveUserEntityMapper.deleteByPrimaryKey(userId, haveUserId);
	}
	
	/**
	 * 友達追加申請を取得する
	 * @param userId 友達追加申請を受けてるユーザーID
	 * @param haveUserId 友達追加申請をしてるユーザーID
	 * @return 友達追加申請
	 * @throws NotFoundException 見つからない
	 */
	public DesireHaveUserEntity getDesireUser(Integer userId, Integer haveUserId) throws NotFoundException {
		DesireHaveUserEntity entity = getDesireUserNonThorw(userId, haveUserId);
		
		if(entity != null)
			return entity;
		else
			throw new NotFoundException("DesireHaveUser");
	}
	
	/**
	 * 友達追加申請リストを取得する
	 * @param userId 友達追加申請を受けてるユーザーID
	 * @return 友達追加申請リスト
	 */
	public List<DesireHaveUserEntity> getDesireUserList(Integer userId) {
		var dto = new DesireHaveUserEntityExample();
		dto
			.or()
				.andUserIdEqualTo(userId);
		
		return desireHaveUserEntityMapper.selectByExample(dto);
	}

	/**
	 * 友達追加申請を取得する<br>
	 * 例外を投げない
	 * @param userId 友達追加申請を受けてるユーザーID
	 * @param haveUserId 友達追加申請をしてるユーザーID
	 * @return 友達追加申請。失敗するとnull
	 */
	public DesireHaveUserEntity getDesireUserNonThorw(Integer userId, Integer haveUserId) {
		return desireHaveUserEntityMapper.selectByPrimaryKey(userId, haveUserId);
	}

	/**
	 * 友達追加申請が、存在するかをチェック<br>
	 * 存在しなくても例外を投げない
	 * @param userId 友達追加申請を受けてるユーザーID
	 * @param haveUserId 友達追加申請をしてるユーザーID
	 * @return 成功ならtrue、失敗ならfalse
	 */
	public boolean isFound(Integer userId, Integer haveUserId) {
		return getDesireUserNonThorw(userId, haveUserId) != null;
	}
	
	/**
	 * 友達追加申請が、存在するかをチェック
	 * @param userId 友達追加申請を受けてるユーザーID
	 * @param haveUserId 友達追加申請をしてるユーザーID
	 * @throws NotFoundException 見つからない
	 */
	public void validationIsFound(Integer userId, Integer haveUserId) throws NotFoundException {
		if(!isFound(userId, haveUserId))
			throw new NotFoundException("DesireHaveUser");
	}
	
	/**
	 * ラストトークインデックスを更新する
	 * @param userId 対象のユーザーID
	 * @param haveUserId 相手のユーザーID
	 * @param lastTalkIndex ラストトークインデックス
	 */
	public void updateLastTalkIndex(Integer userId, Integer haveUserId, Integer lastTalkIndex) {
		//データ作成
		var entity = new DesireHaveUserEntity();
		entity.setLastTalkIndex(lastTalkIndex);
		
		//SQL作成
		var dto = new DesireHaveUserEntityExample();
		dto
			.or()
				.andUserIdEqualTo(userId)
				.andHaveUserIdEqualTo(haveUserId)
				.andLastTalkIndexLessThan(lastTalkIndex);
		
		//処理
		desireHaveUserEntityMapper.updateByExampleSelective(entity, dto);
	}

	/**
	 * 友達追加申請を登録する
	 * @param userId ユーザーID
	 * @param haveUserId 申請を出してるユーザーのID
	 * @param dialogueTalkRoomId 友達トークID
	 * @param lastTalkIndex ラストトークインデックス
	 */
	public void insert(Integer userId, Integer haveUserId, Integer dialogueTalkRoomId, Integer lastTalkIndex) {
		//データ作成
		var entity = new DesireHaveUserEntity();
		entity.setUserId(userId);
		entity.setHaveUserId(haveUserId);
		entity.setTalkRoomId(dialogueTalkRoomId);
		entity.setLastTalkIndex(lastTalkIndex);
		
		//処理
		desireHaveUserEntityMapper.insert(entity);
	}
}