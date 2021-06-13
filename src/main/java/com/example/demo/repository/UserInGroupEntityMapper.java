package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.UserInGroupEntityExample;
import com.example.demo.entity.UserInGroupEntity;

@Mapper
public interface UserInGroupEntityMapper {

    long countByExample(UserInGroupEntityExample example);

    int deleteByExample(UserInGroupEntityExample example);

    int deleteByPrimaryKey(@Param("talkRoomId") Integer talkRoomId, @Param("userId") Integer userId);

    int insert(UserInGroupEntity record);

    int insertSelective(UserInGroupEntity record);

    List<UserInGroupEntity> selectByExample(UserInGroupEntityExample example);

    UserInGroupEntity selectByPrimaryKey(@Param("talkRoomId") Integer talkRoomId, @Param("userId") Integer userId);

    int updateByExampleSelective(@Param("record") UserInGroupEntity record, @Param("example") UserInGroupEntityExample example);

    int updateByExample(@Param("record") UserInGroupEntity record, @Param("example") UserInGroupEntityExample example);

    int updateByPrimaryKeySelective(UserInGroupEntity record);

    int updateByPrimaryKey(UserInGroupEntity record);
}