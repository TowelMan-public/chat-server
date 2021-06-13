package com.example.demo.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DesireHaveUserEntity {
    
    private Integer userId;

    private Integer haveUserId;

    private Integer talkRoomId;
    
    private Integer lastTalkIndex;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHaveUserId() {
        return haveUserId;
    }
    
    public void setHaveUserId(Integer haveUserId) {
        this.haveUserId = haveUserId;
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