package com.example.demo.entity.response;

import java.text.SimpleDateFormat;

import com.example.demo.entity.TalkEntity;
import com.example.demo.entity.UserEntity;

import lombok.Data;

/**
 * トークを取得するAPIのレスポンスとして返すエンティティー
 */
@Data
public class TalkResponse {
	private Integer talkIndex;
	private String userIdName;
	private String content;
	private String timestampString;
	
	/**
	 * コンストラクタ<br>
	 * トークとユーザー情報を合わせる
	 * @param talkEntity
	 * @param userEntity
	 */
	public TalkResponse(TalkEntity talkEntity, UserEntity userEntity) {
		talkIndex = talkEntity.getTalkIndex();
		userIdName = userEntity.getUserIdName();
		content = talkEntity.getContent();
		var format = new SimpleDateFormat("yyy/M/d/HH/m");
		timestampString = format.format(talkEntity.getTimestamp());
	}
}
