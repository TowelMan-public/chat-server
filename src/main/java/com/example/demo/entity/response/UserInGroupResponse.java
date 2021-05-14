package com.example.demo.entity.response;

import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserInGroupEntity;

import lombok.Data;

/**
 * グループ加入者を取得するAPIのレスポンスとして返すエンティティー
 */
@Data
public class UserInGroupResponse {
	private Integer talkRoomId;
	private Integer lastTalkIndex;
	private String userIdName;
	
	/**
	 * コンストラクタ<br>
	 * グループ加入者とユーザー情報を合わせる
	 * @param userInGroupEntity グループ加入者
	 * @param userEntity ユーザー情報
	 */
	public UserInGroupResponse(UserInGroupEntity userInGroupEntity, UserEntity userEntity) {
		talkRoomId = userInGroupEntity.getTalkRoomId();
		lastTalkIndex = userInGroupEntity.getLastTalkIndex();
		userIdName = userEntity.getUserIdName();
	}
}
