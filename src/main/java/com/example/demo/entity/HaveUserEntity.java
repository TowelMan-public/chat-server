package com.example.demo.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class HaveUserEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column have_user_list.user_id
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column have_user_list.have_user_id
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    private Integer haveUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column have_user_list.talk_room_id
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    private Integer talkRoomId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column have_user_list.last_talk_index
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    private Integer lastTalkIndex;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column have_user_list.user_id
     *
     * @return the value of have_user_list.user_id
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column have_user_list.user_id
     *
     * @param userId the value for have_user_list.user_id
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column have_user_list.have_user_id
     *
     * @return the value of have_user_list.have_user_id
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    public Integer getHaveUserId() {
        return haveUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column have_user_list.have_user_id
     *
     * @param haveUserId the value for have_user_list.have_user_id
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    public void setHaveUserId(Integer haveUserId) {
        this.haveUserId = haveUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column have_user_list.talk_room_id
     *
     * @return the value of have_user_list.talk_room_id
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    public Integer getTalkRoomId() {
        return talkRoomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column have_user_list.talk_room_id
     *
     * @param talkRoomId the value for have_user_list.talk_room_id
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    public void setTalkRoomId(Integer talkRoomId) {
        this.talkRoomId = talkRoomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column have_user_list.last_talk_index
     *
     * @return the value of have_user_list.last_talk_index
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    public Integer getLastTalkIndex() {
        return lastTalkIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column have_user_list.last_talk_index
     *
     * @param lastTalkIndex the value for have_user_list.last_talk_index
     *
     * @mbg.generated Mon May 17 23:53:47 JST 2021
     */
    public void setLastTalkIndex(Integer lastTalkIndex) {
        this.lastTalkIndex = lastTalkIndex;
    }
}