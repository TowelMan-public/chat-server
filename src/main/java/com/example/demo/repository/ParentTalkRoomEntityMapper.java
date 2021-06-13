package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.ParentTalkRoomEntityExample;
import com.example.demo.entity.ParentTalkRoomEntity;

@Mapper
public interface ParentTalkRoomEntityMapper {

    long countByExample(ParentTalkRoomEntityExample example);

    int deleteByExample(ParentTalkRoomEntityExample example);

    int deleteByPrimaryKey(Integer talkRoomId);

    int insert(ParentTalkRoomEntity record);

    int insertSelective(ParentTalkRoomEntity record);

    List<ParentTalkRoomEntity> selectByExample(ParentTalkRoomEntityExample example);

    int updateByExampleSelective(@Param("record") ParentTalkRoomEntity record, @Param("example") ParentTalkRoomEntityExample example);

    int updateByExample(@Param("record") ParentTalkRoomEntity record, @Param("example") ParentTalkRoomEntityExample example);
}