package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.ParentUserEntityExample;
import com.example.demo.entity.ParentUserEntity;

@Mapper
public interface ParentUserEntityMapper {

	long countByExample(ParentUserEntityExample example);

	int deleteByExample(ParentUserEntityExample example);

	int deleteByPrimaryKey(Integer userId);

	int insert(ParentUserEntity record);

	int insertSelective(ParentUserEntity record);

	List<ParentUserEntity> selectByExample(ParentUserEntityExample example);

	ParentUserEntity selectByPrimaryKey(Integer userId);

	int updateByExampleSelective(@Param("record") ParentUserEntity record,
			@Param("example") ParentUserEntityExample example);

	int updateByExample(@Param("record") ParentUserEntity record, @Param("example") ParentUserEntityExample example);

	int updateByPrimaryKeySelective(ParentUserEntity record);

	int updateByPrimaryKey(ParentUserEntity record);
}