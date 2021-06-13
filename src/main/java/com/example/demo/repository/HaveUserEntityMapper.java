package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.HaveUserEntityExample;
import com.example.demo.entity.HaveUserEntity;

@Mapper
public interface HaveUserEntityMapper {

	long countByExample(HaveUserEntityExample example);

	int deleteByExample(HaveUserEntityExample example);


	int deleteByPrimaryKey(Integer userId);

	int insert(HaveUserEntity record);

	int insertSelective(HaveUserEntity record);

	List<HaveUserEntity> selectByExample(HaveUserEntityExample example);

	HaveUserEntity selectByPrimaryKey(Integer userId);

	int updateByExampleSelective(@Param("record") HaveUserEntity record,
			@Param("example") HaveUserEntityExample example);

	int updateByExample(@Param("record") HaveUserEntity record, @Param("example") HaveUserEntityExample example);

	int updateByPrimaryKeySelective(HaveUserEntity record);

	int updateByPrimaryKey(HaveUserEntity record);
}