package com.example.demo.entity.response;

import com.example.demo.entity.HaveUserEntity;
import com.example.demo.entity.UserEntity;

import lombok.Data;

/**
 * 友達リストを取得するAPIのレスポンスとして返すエンティティー
 */
@Data
public class HaveUserResponse {
	private String haveUserIdName;
	private String haveUserName;
	private Integer talkRoomId;
	private Integer lastTalkIndex;
	
	/**
	 * コンストラクタ<br>
	 * 友達とユーザー情報を合わせる
	 * @param haveUserEntity　友達
	 * @param userEntity 申請しているユーザー情報
	 */
	public HaveUserResponse(HaveUserEntity haveUserEntity, UserEntity userEntity) {
		haveUserIdName = userEntity.getUserIdName();
		haveUserName = userEntity.getUserName();
		talkRoomId = haveUserEntity.getTalkRoomId();
		lastTalkIndex = haveUserEntity.getLastTalkIndex();
	}
}
