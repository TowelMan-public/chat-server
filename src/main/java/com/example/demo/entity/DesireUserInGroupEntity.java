package com.example.demo.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DesireUserInGroupEntity {

    private Integer userId;

    private Integer talkRoomId;

    private Integer lastTalkIndex;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
}