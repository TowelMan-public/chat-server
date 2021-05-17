package com.example.demo.entity.response;

import com.example.demo.entity.GroupTalkRoomEntity;
import com.example.demo.entity.UserInGroupEntity;

import lombok.Data;

@Data
public class GroupTalkRoomResponse {
	private Integer talkRoomId;
	private String groupName;
	private Integer groupLastTalkIndex;
	private Integer userLastTalkIndex;
	
	public GroupTalkRoomResponse(GroupTalkRoomEntity groupEntity, UserInGroupEntity userInGroupEntity) {
		talkRoomId = groupEntity.getTalkRoomId();
		groupName = groupEntity.getGroupName();
		groupLastTalkIndex = groupEntity.getLastTalkIndex();
		userLastTalkIndex = userInGroupEntity.getLastTalkIndex();
	}
}
