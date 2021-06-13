package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.TalkEntityExample;
import com.example.demo.entity.TalkEntity;

@Mapper
public interface TalkEntityMapper {

    long countByExample(TalkEntityExample example);

    int deleteByExample(TalkEntityExample example);

    int deleteByPrimaryKey(@Param("talkRoomId") Integer talkRoomId, @Param("talkIndex") Integer talkIndex);

    int insert(TalkEntity record);

    int insertSelective(TalkEntity record);

    List<TalkEntity> selectByExample(TalkEntityExample example);

    TalkEntity selectByPrimaryKey(@Param("talkRoomId") Integer talkRoomId, @Param("talkIndex") Integer talkIndex);

    int updateByExampleSelective(@Param("record") TalkEntity record, @Param("example") TalkEntityExample example);

    int updateByExample(@Param("record") TalkEntity record, @Param("example") TalkEntityExample example);

    int updateByPrimaryKeySelective(TalkEntity record);

    int updateByPrimaryKey(TalkEntity record);
}