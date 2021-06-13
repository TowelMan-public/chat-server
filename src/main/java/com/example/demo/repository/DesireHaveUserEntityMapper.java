package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.DesireHaveUserEntityExample;
import com.example.demo.entity.DesireHaveUserEntity;

@Mapper
public interface DesireHaveUserEntityMapper {

    long countByExample(DesireHaveUserEntityExample example);

    int deleteByExample(DesireHaveUserEntityExample example);

    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("haveUserId") Integer haveUserId);

    int insert(DesireHaveUserEntity record);

    int insertSelective(DesireHaveUserEntity record);

    List<DesireHaveUserEntity> selectByExample(DesireHaveUserEntityExample example);

    DesireHaveUserEntity selectByPrimaryKey(@Param("userId") Integer userId, @Param("haveUserId") Integer haveUserId);

    int updateByExampleSelective(@Param("record") DesireHaveUserEntity record, @Param("example") DesireHaveUserEntityExample example);

    int updateByExample(@Param("record") DesireHaveUserEntity record, @Param("example") DesireHaveUserEntityExample example);

    int updateByPrimaryKeySelective(DesireHaveUserEntity record);

    int updateByPrimaryKey(DesireHaveUserEntity record);
}