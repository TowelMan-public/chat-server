package com.example.demo.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class GroupTalkRoomEntity {

	private Integer talkRoomId;

	private String groupName;

	private Integer lastTalkIndex;

	private Boolean isEnabled;

	public Integer getTalkRoomId() {
		return talkRoomId;
	}

	public void setTalkRoomId(Integer talkRoomId) {
		this.talkRoomId = talkRoomId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getLastTalkIndex() {
		return lastTalkIndex;
	}

	public void setLastTalkIndex(Integer lastTalkIndex) {
		this.lastTalkIndex = lastTalkIndex;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}


	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}