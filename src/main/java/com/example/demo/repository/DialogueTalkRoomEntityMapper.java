package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.DialogueTalkRoomEntityExample;
import com.example.demo.entity.DialogueTalkRoomEntity;

@Mapper
public interface DialogueTalkRoomEntityMapper {

    long countByExample(DialogueTalkRoomEntityExample example);

    int deleteByExample(DialogueTalkRoomEntityExample example);

    int deleteByPrimaryKey(Integer talkRoomId);

    int insert(DialogueTalkRoomEntity record);

    int insertSelective(DialogueTalkRoomEntity record);

    List<DialogueTalkRoomEntity> selectByExample(DialogueTalkRoomEntityExample example);

    DialogueTalkRoomEntity selectByPrimaryKey(Integer talkRoomId);

    int updateByExampleSelective(@Param("record") DialogueTalkRoomEntity record, @Param("example") DialogueTalkRoomEntityExample example);

    int updateByExample(@Param("record") DialogueTalkRoomEntity record, @Param("example") DialogueTalkRoomEntityExample example);

    int updateByPrimaryKeySelective(DialogueTalkRoomEntity record);

    int updateByPrimaryKey(DialogueTalkRoomEntity record);

    void incrementLastTalkIndex(Integer talkRoomId);
}