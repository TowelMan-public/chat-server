package com.example.demo.entity;

import java.util.Date;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude={"timestamp"})
public class TalkEntity {
    private Integer talkRoomId;

    private Integer talkIndex;
    
    private Integer userId;

    private String content;

    private Date timestamp;

    public Integer getTalkRoomId() {
        return talkRoomId;
    }

    public void setTalkRoomId(Integer talkRoomId) {
        this.talkRoomId = talkRoomId;
    }

    public Integer getTalkIndex() {
        return talkIndex;
    }

    public void setTalkIndex(Integer talkIndex) {
        this.talkIndex = talkIndex;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}