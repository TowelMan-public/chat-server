package com.example.demo.entity;

public class DesireUserInGroupEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_desire.user_id
     *
     * @mbg.generated Sun May 09 20:59:53 JST 2021
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_desire.talk_room_id
     *
     * @mbg.generated Sun May 09 20:59:53 JST 2021
     */
    private Integer talkRoomId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_desire.last_talk_index
     *
     * @mbg.generated Sun May 09 20:59:53 JST 2021
     */
    private Integer lastTalkIndex;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_desire.user_id
     *
     * @return the value of group_desire.user_id
     *
     * @mbg.generated Sun May 09 20:59:53 JST 2021
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_desire.user_id
     *
     * @param userId the value for group_desire.user_id
     *
     * @mbg.generated Sun May 09 20:59:53 JST 2021
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_desire.talk_room_id
     *
     * @return the value of group_desire.talk_room_id
     *
     * @mbg.generated Sun May 09 20:59:53 JST 2021
     */
    public Integer getTalkRoomId() {
        return talkRoomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_desire.talk_room_id
     *
     * @param talkRoomId the value for group_desire.talk_room_id
     *
     * @mbg.generated Sun May 09 20:59:53 JST 2021
     */
    public void setTalkRoomId(Integer talkRoomId) {
        this.talkRoomId = talkRoomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_desire.last_talk_index
     *
     * @return the value of group_desire.last_talk_index
     *
     * @mbg.generated Sun May 09 20:59:53 JST 2021
     */
    public Integer getLastTalkIndex() {
        return lastTalkIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_desire.last_talk_index
     *
     * @param lastTalkIndex the value for group_desire.last_talk_index
     *
     * @mbg.generated Sun May 09 20:59:53 JST 2021
     */
    public void setLastTalkIndex(Integer lastTalkIndex) {
        this.lastTalkIndex = lastTalkIndex;
    }
}