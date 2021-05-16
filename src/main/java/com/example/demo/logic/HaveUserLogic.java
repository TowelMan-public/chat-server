package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.HaveUserEntityExample;
import com.example.demo.entity.DesireHaveUserEntity;
import com.example.demo.entity.HaveUserEntity;
import com.example.demo.exception.AlreadyHaveUserException;
import com.example.demo.exception.NotHaveUserException;
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

	/**
	 * 友達登録していないかかのチェック
	 * @param userId 友達登録している側のユーザーID
	 * @param haveUserId 友達登録されている側のユーザーID
	 * @throws AlreadyHaveUserException 既に登録している
	 */
	public void validationNotInsertedOne(Integer userId, Integer haveUserId) throws AlreadyHaveUserException {
		if(isInsertedOne(userId, haveUserId))
			throw new AlreadyHaveUserException();
	}

	/**
	 * 友達登録しているかのチェック
	 * @param userId 友達登録している側のユーザーID
	 * @param haveUserId 友達登録されている側のユーザーID
	 * @throws NotHaveUserException 登録してない
	 */
	public void validationInsertedOne(Integer userId, Integer haveUserId) throws NotHaveUserException {
		if(!isInsertedOne(userId, haveUserId))
			throw new NotHaveUserException();
	}
	
	/**
	 * 友達登録しているかのチェック
	 * @param userId 友達登録している側のユーザーID
	 * @param haveUserId 友達登録されている側のユーザーID
	 * @return 登録してればtrue、してなければfalse
	 */
	public boolean isInsertedOne(Integer userId, Integer haveUserId) {
		//SQL作成
		var dto = new HaveUserEntityExample();
		dto
			.or()
				.andUserIdEqualTo(userId)
				.andHaveUserIdEqualTo(haveUserId);
		
		//処理
		return !haveUserEntityMapper.selectByExample(dto)
									.isEmpty();
	}

	/**
	 * 友達登録をする
	 * @param userId 友達登録する側のユーザーID
	 * @param haveUserId 友達登録される側のユーザーID
	 * @param dialogueTalkRoomId 友達トークルームID
	 * @param lastTalkIndex 最後のトークID
	 */
	public void insert(Integer userId, Integer haveUserId, Integer dialogueTalkRoomId, Integer lastTalkIndex) {
		var entity = new HaveUserEntity();
		
		//データセット
		entity.setUserId(userId);
		entity.setHaveUserId(haveUserId);
		entity.setTalkRoomId(dialogueTalkRoomId);
		entity.setLastTalkIndex(lastTalkIndex);
		
		//処理
		haveUserEntityMapper.insert(entity);
	}

	
	/**
	 * 友達を取得する
	 * @param userId 友達登録する側のユーザーID
	 * @param haveUserId 友達登録される側のユーザーID
	 * @return 友達
	 * @throws NotHaveUserException 登録されてない
	 */
	public HaveUserEntity getHaveUser(Integer userId, Integer haveUserId) throws NotHaveUserException {
		//SQL作成
		var dto = new HaveUserEntityExample();
		dto
			.or()
				.andUserIdEqualTo(userId)
				.andHaveUserIdEqualTo(haveUserId);
		
		List<HaveUserEntity> list = haveUserEntityMapper.selectByExample(dto);
		
		if(list.isEmpty())
			throw new NotHaveUserException();
		else
			return list.get(0);
	}

	/**
	 * 友達を削除する
	 * @param userId 友達登録する側のユーザーID
	 * @param haveUserId 友達登録される側のユーザーID
	 */
	public void delete(Integer userId, Integer haveUserId) {
		//SQL作成
		var dto = new HaveUserEntityExample();
		dto
			.or()
				.andUserIdEqualTo(userId)
				.andHaveUserIdEqualTo(haveUserId);
		
		//処理
		haveUserEntityMapper.deleteByExample(dto);
	}

	/**
	 * 友達リストを取得する
	 * @param userId ユーザーID
	 * @return 友達リスト
	 */
	public List<HaveUserEntity> getHaveUserList(Integer userId) {
		//SQL作成
		var dto = new HaveUserEntityExample();
		dto
			.or()
				.andUserIdEqualTo(userId);
		
		//処理
		return haveUserEntityMapper.selectByExample(dto);
	}

	public void updateLastTalkIndex(Integer userId, Integer haveUserId, Integer lastTalkIndex) {
		//データ作成
		var entity = new HaveUserEntity();
		entity.setLastTalkIndex(lastTalkIndex);
		
		//SQL作成
		var dto = new HaveUserEntityExample();
		dto
			.or()
				.andUserIdEqualTo(userId)
				.andHaveUserIdEqualTo(haveUserId)
				.andLastTalkIndexLessThan(lastTalkIndex);
		
		//処理
		haveUserEntityMapper.updateByExampleSelective(entity, dto);
	}
}