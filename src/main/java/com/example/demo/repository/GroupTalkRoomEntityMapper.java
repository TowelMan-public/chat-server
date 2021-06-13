package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.GroupTalkRoomEntityExample;
import com.example.demo.entity.GroupTalkRoomEntity;

@Mapper
public interface GroupTalkRoomEntityMapper {

    long countByExample(GroupTalkRoomEntityExample example);

    int deleteByExample(GroupTalkRoomEntityExample example);

    int deleteByPrimaryKey(Integer talkRoomId);

    int insert(GroupTalkRoomEntity record);

    int insertSelective(GroupTalkRoomEntity record);

    List<GroupTalkRoomEntity> selectByExample(GroupTalkRoomEntityExample example);

    GroupTalkRoomEntity selectByPrimaryKey(Integer talkRoomId);

    int updateByExampleSelective(@Param("record") GroupTalkRoomEntity record, @Param("example") GroupTalkRoomEntityExample example);

    int updateByExample(@Param("record") GroupTalkRoomEntity record, @Param("example") GroupTalkRoomEntityExample example);

    int updateByPrimaryKeySelective(GroupTalkRoomEntity record);

    int updateByPrimaryKey(GroupTalkRoomEntity record);
    
    /**
     * 自作<br>
     * last_talk_indexカラムをインクリメントする
     * 
     * @param talkRoomId talk_room_id の値
     */
    void incrementLastTalkIndex(Integer talkRoomId);
}