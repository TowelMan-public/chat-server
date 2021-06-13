package com.example.demo.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ParentTalkRoomEntity {

    private Integer talkRoomId;

    public Integer getTalkRoomId() {
        return talkRoomId;
    }

    public void setTalkRoomId(Integer talkRoomId) {
        this.talkRoomId = talkRoomId;
    }
}