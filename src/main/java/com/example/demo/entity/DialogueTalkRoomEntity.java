package com.example.demo.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DialogueTalkRoomEntity {

	private Integer talkRoomId;

	private Integer lastTalkIndex;

	private Boolean isEnabled;

	public Integer getTalkRoomId() {
		return talkRoomId;
	}

	public void setTalkRoomId(Integer talkRoomId) {
		this.talkRoomId = talkRoomId;
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