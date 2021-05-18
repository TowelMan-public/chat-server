package com.example.demo.logic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TalkEntityExample;
import com.example.demo.entity.TalkEntity;
import com.example.demo.exception.BadRequestFormException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.TalkEntityMapper;


/**
 * トーク周りの共通処理クラス
 */
@Component
public class Talklogic {

	@Autowired
	TalkEntityMapper talkEntityMapper;
	
	/**
	 * トークリストを取得する
	 * @param talkRoomId トークルームID 
	 * @param startIndex 取得するトークの始まりのインデックス
	 * @param maxSize 最大で取得するトークの件数
	 * @return トークリスト
	 */
	public List<TalkEntity> getTalks(Integer talkRoomId, Integer startIndex, Integer maxSize) {
		//SQL作成
		var dto = new TalkEntityExample();
		dto
			.setOrderByClause("talk_index");
		dto
			.or()
				.andTalkRoomIdEqualTo(talkRoomId)
				
				.andTalkIndexGreaterThanOrEqualTo(startIndex)
				.andTalkIndexLessThan( startIndex + maxSize );
		
		return talkEntityMapper.selectByExample(dto);
	}

	/**
	 * トークを追加する
	 * @param talkRoomId トークルームID
	 * @param userId トークを作成したユーザーのID
	 * @param talkContentText 内容
	 * @param talkIndex トークインデックス
	 */
	public void insert(Integer talkRoomId, Integer userId, String talkContentText, Integer talkIndex) {
		//データ作成
		var entity = new TalkEntity();
		entity.setTalkRoomId(talkRoomId);
		entity.setUserId(userId);
		entity.setContent(talkContentText);
		entity.setTalkIndex(talkIndex);
		entity.setTimestamp(new Date());
		
		//処理
		talkEntityMapper.insert(entity);
	}

	/**
	 * トークの内容変更
	 * @param talkRoomId トークルームID
	 * @param talkIndex　トークインデックス
	 * @param talkContentText　内容
	 */
	public void update(Integer talkRoomId, Integer talkIndex, String talkContentText) {
		//データ作成
		var entity = new TalkEntity();
		entity.setTalkRoomId(talkRoomId);
		entity.setContent(talkContentText);
		entity.setTalkIndex(talkIndex);
		
		//処理
		talkEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * トークの削除
	 * @param talkRoomId トークルームID
	 * @param talkIndex　トークインデックス
	 */
	public void delete(Integer talkRoomId, Integer talkIndex) {
		talkEntityMapper.deleteByPrimaryKey(talkRoomId, talkIndex);
	}

	/**
	 * トークがあるかを検証する
	 * @param talkRoomId トークルームID
	 * @param talkIndex　トークインデックス
	 * @throws NotFoundException 見つからない
	 */
	public void validationIsFound(Integer talkRoomId, Integer talkIndex) throws NotFoundException {
		TalkEntity entity = talkEntityMapper.selectByPrimaryKey(talkRoomId, talkIndex);
		if(entity == null)
			throw new NotFoundException("talkIndex");
	}

	/**
	 * トークルームがあり、かつ指定されたユーザーが作ったかを検証
	 * @param talkRoomId トークルームID
	 * @param talkIndex　トークインデックス
	 * @param userId トークを作成したユーザーのID
	 * @throws BadRequestFormException　指定されたユーザー以外がトークを作成している
	 * @throws NotFoundException 見つからない
	 */
	public void validationIsEnabled(Integer talkRoomId, Integer talkIndex, Integer userId) throws BadRequestFormException, NotFoundException {
		TalkEntity entity = talkEntityMapper.selectByPrimaryKey(talkRoomId, talkIndex);
		if(entity == null)
			throw new NotFoundException("talkIndex");
		else if(entity.getUserId().equals(userId))
			throw new BadRequestFormException("This talk's user is not match your 'haveUserIdName'!");
	}

	/**
	 * トークを取得する
	 * @param talkRoomId トークルームID
	 * @param talkIndex　トークインデックス
	 * @return トーク
	 * @throws NotFoundException 見つからない
	 */
	public TalkEntity getTalk(Integer talkRoomId, Integer talkIndex) throws NotFoundException {
		TalkEntity entity = talkEntityMapper.selectByPrimaryKey(talkRoomId, talkIndex);
		if(entity == null)
			throw new NotFoundException("talkIndex");
		else
			return entity;
	}
}