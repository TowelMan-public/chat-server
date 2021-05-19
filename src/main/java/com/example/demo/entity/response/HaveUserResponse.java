package com.example.demo.entity.response;

import com.example.demo.entity.DialogueTalkRoomEntity;
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
	private Integer talkLastTalkIndex;
	private Integer myLastTalkIndex;
	
	/**
	 * コンストラクタ<br>
	 * 友達とユーザー情報を合わせる
	 * @param haveUserEntity　友達
	 * @param userEntity 申請しているユーザー情報
	 * @param dialogueEntity トークルーム
	 */
	public HaveUserResponse(HaveUserEntity haveUserEntity, UserEntity userEntity, DialogueTalkRoomEntity dialogueEntity) {
		haveUserIdName = userEntity.getUserIdName();
		haveUserName = userEntity.getUserName();
		talkRoomId = haveUserEntity.getTalkRoomId();
		myLastTalkIndex = haveUserEntity.getLastTalkIndex();
		talkLastTalkIndex = dialogueEntity.getLastTalkIndex();
	}

	/**
	 * テスト用
	 */
	public HaveUserResponse() {}
}
