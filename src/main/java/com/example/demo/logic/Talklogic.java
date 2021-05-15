package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TalkEntityExample;
import com.example.demo.entity.TalkEntity;
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

}
