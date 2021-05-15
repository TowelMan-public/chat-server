package com.example.demo.entity.response;

import com.example.demo.entity.DesireHaveUserEntity;
import com.example.demo.entity.UserEntity;

import lombok.Data;

/**
 * 友達追加申請リストを取得するAPIのレスポンスとして返すエンティティー
 */
@Data
public class DesireHaveUserResponse {
	private String haveUserIdName;
	private String haveUserName;
	private Integer talkRoomId;
	private Integer lastTalkIndex;
	
	/**
	 * コンストラクタ<br>
	 * 友達追加申請とユーザー情報を合わせる
	 * @param desireEntity　友達追加申請
	 * @param haveEntity 申請しているユーザー情報
	 */
	public DesireHaveUserResponse(DesireHaveUserEntity desireEntity, UserEntity haveEntity) {
		haveUserIdName = haveEntity.getUserIdName();
		haveUserName = haveEntity.getUserName();
		talkRoomId = desireEntity.getTalkRoomId();
		lastTalkIndex = desireEntity.getLastTalkIndex();
	}
}
