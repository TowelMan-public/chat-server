package com.example.demo.entity.response;

import com.example.demo.entity.DesireUserInGroupEntity;
import com.example.demo.entity.GroupTalkRoomEntity;

import lombok.Data;

/**
 * グループ加入申請リストを取得するAPIのレスポンスとして返すエンティティー
 */
@Data
public class DesireUserInGroupResponce {
	private Integer talkRoomId;
	private Integer lastTalkIndex;
	private String groupName;
	
	/**
	 * コンストラクタ<br>
	 * グループ加入申請とグループ情報を合わせる
	 * @param desireEntity グループ加入申請
	 * @param groupEntity グループ情報
	 */
	public DesireUserInGroupResponce(DesireUserInGroupEntity desireEntity, GroupTalkRoomEntity groupEntity) {
		talkRoomId = desireEntity.getTalkRoomId();
		lastTalkIndex = desireEntity.getLastTalkIndex();
		groupName = groupEntity.getGroupName();
	}
}
