package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.DesireUserInGroupEntityExample;
import com.example.demo.entity.DesireUserInGroupEntity;

@Mapper
public interface DesireUserInGroupEntityMapper {
	
    long countByExample(DesireUserInGroupEntityExample example);
    
    int deleteByExample(DesireUserInGroupEntityExample example);

    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("talkRoomId") Integer talkRoomId);

    int insert(DesireUserInGroupEntity record);

    int insertSelective(DesireUserInGroupEntity record);

    List<DesireUserInGroupEntity> selectByExample(DesireUserInGroupEntityExample example);

    DesireUserInGroupEntity selectByPrimaryKey(@Param("userId") Integer userId, @Param("talkRoomId") Integer talkRoomId);

    int updateByExampleSelective(@Param("record") DesireUserInGroupEntity record, @Param("example") DesireUserInGroupEntityExample example);

    int updateByExample(@Param("record") DesireUserInGroupEntity record, @Param("example") DesireUserInGroupEntityExample example);

    int updateByPrimaryKeySelective(DesireUserInGroupEntity record);

    int updateByPrimaryKey(DesireUserInGroupEntity record);
}