package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.DeletedUserEntityExample;
import com.example.demo.dto.UserEntityExample;
import com.example.demo.entity.ParentUserEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnEnableException;
import com.example.demo.repository.DeletedUserEntityMapper;
import com.example.demo.repository.ParentUserEntityMapper;
import com.example.demo.repository.UserEntityMapper;

@Component
public class UserLogic {

	@Autowired
	ParentUserEntityMapper parentUserEntityMapper;
	@Autowired
	UserEntityMapper userEntityMapper;
	@Autowired
	DeletedUserEntityMapper deletedUserEntityMapper;
	
	public UserEntity getUserByUserIdName(String userIdName) throws NotFoundException, UnEnableException {
		UserEntityExample dto = new UserEntityExample();
		dto
			.or()
			.andUserIdNameEqualTo(userIdName);
		
		List<UserEntity> list = userEntityMapper.selectByExample(dto);
		if(list.size() == 1) {
			UserEntity entity = list.get(0);
			validationIsEnable(entity.getUserId());
			return entity;
		}else {
			throw new NotFoundException("userIdName");
		}
	}

	public UserEntity getUserByUserId(Integer userId) throws UnEnableException, NotFoundException {
		UserEntity entity = userEntityMapper.selectByPrimaryKey(userId);
		
		if(entity != null) {
			validationIsEnable(entity.getUserId());
			return entity;
		}else {
			throw new NotFoundException("userId");
		}
	}
	
	public void validationIsFound(Integer userId) throws UnEnableException, NotFoundException {
		ParentUserEntity parentEntity = parentUserEntityMapper.selectByPrimaryKey(userId);
		
		if(parentEntity != null && parentEntity.getUserId() != null) {
			validationIsEnable(userId);
		}else {
			throw new NotFoundException("userId");
		}
	}
	
	public void validationIsEnable(Integer userId) throws UnEnableException {
		DeletedUserEntityExample dto = new DeletedUserEntityExample();
		dto
			.or()
			.andUserIdEqualTo(userId);
		
		if(deletedUserEntityMapper.countByExample(dto) != 0)
			throw new UnEnableException("UserEntity");
	}
}
