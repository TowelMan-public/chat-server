package com.example.demo.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserInGroupEntity {

    private Integer talkRoomId;

    private Integer userId;

    private Integer lastTalkIndex;

    public Integer getTalkRoomId() {
        return talkRoomId;
    }

    public void setTalkRoomId(Integer talkRoomId) {
        this.talkRoomId = talkRoomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLastTalkIndex() {
        return lastTalkIndex;
    }

    public void setLastTalkIndex(Integer lastTalkIndex) {
        this.lastTalkIndex = lastTalkIndex;
    }
}