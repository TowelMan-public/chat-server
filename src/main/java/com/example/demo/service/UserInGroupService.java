package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserInGroupEntity;
import com.example.demo.entity.response.UserInGroupResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotInsertedGroupDesireException;
import com.example.demo.exception.NotJoinGroupException;
import com.example.demo.logic.DesireUserInGroupLogic;
import com.example.demo.logic.GroupLogic;
import com.example.demo.logic.UserInGroupLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;

/**
 * グループ加入者に関するAPIのサービスロジック
 */
@Service
public class UserInGroupService {

	@Autowired
	UserLogic userLogic;
	@Autowired
	UserInGroupLogic userInGroupLogic;
	@Autowired
	GroupLogic groupLogic;
	@Autowired
	DesireUserInGroupLogic desireUserInGroupLogic;
	
	/**
	 * ユーザーをグループに加入させる（グループに加入してほしい申請の登録）
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームId
	 * @param userIdNameInGroup グループに加入させたいユーザーID
	 * @throws NotFoundException
	 * @throws NotJoinGroupException
	 * @throws NotInsertedGroupDesireException
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void insertUserInGroup(UserDetailsImp user, Integer talkRoomId, String userIdNameInGroup) throws NotFoundException, NotJoinGroupException, NotInsertedGroupDesireException {
		//チェック・データ取得
		userInGroupLogic.validationJoinGroup(talkRoomId,user.getUserId());
		Integer userIdInGroup = userLogic.getUserByUserIdName(userIdNameInGroup)
											.getUserId();
		desireUserInGroupLogic.validationInserted(talkRoomId, userIdInGroup);
		
		//データ取得
		Integer lastIndex = groupLogic.getGroupNonThrows(talkRoomId)
										.getLastTalkIndex();
		
		//処理
		desireUserInGroupLogic.insert(talkRoomId,userIdInGroup,lastIndex);
	}

	/**
	 * グループに加入しているユーザーリストを取得する
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームId
	 * @return レスポンス用のグループに加入しているユーザーリスト
	 * @throws NotJoinGroupException
	 */
	public List<UserInGroupResponse> getUsersInGroup(UserDetailsImp user, Integer talkRoomId) throws NotJoinGroupException {
		//チェック
		userInGroupLogic.validationJoinGroup(talkRoomId,user.getUserId());
		
		//データ取得・宣言
		List<UserInGroupEntity> userInGroupList = userInGroupLogic.getUserInGroupList(talkRoomId);
		List<UserInGroupResponse> responseList = new ArrayList<>();
		
		//処理
		for(UserInGroupEntity userInGroupEntity: userInGroupList) {
			var userEntity = userLogic.getUserByUserIdNonThrow(userInGroupEntity.getUserId());
			if(userEntity != null)
				responseList.add(new UserInGroupResponse(userInGroupEntity,userEntity));
		}
		
		return responseList;
	}

	/**
	 * グループに加入しているユーザーを脱退させる
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームId
	 * @param userIdNameInGroup グループに加入しているユーザーID
	 * @throws NotFoundException
	 * @throws NotJoinGroupException
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void deleteUserInGroup(UserDetailsImp user, Integer talkRoomId, String userIdNameInGroup) throws NotFoundException, NotJoinGroupException {
		//チェック
		userInGroupLogic.validationJoinGroup(talkRoomId,user.getUserId());
		Integer userIdInGroup = userLogic.getUserByUserIdName(userIdNameInGroup)
				.getUserId();
		userInGroupLogic.validationJoinGroup(talkRoomId,userIdInGroup);
		
		//処理
		userInGroupLogic.delete(talkRoomId, userIdInGroup);
	}

	/**
	 * グループから脱退する
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームId
	 * @throws NotJoinGroupException
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void exitGroup(UserDetailsImp user, Integer talkRoomId) throws NotJoinGroupException {
		//チェック
		userInGroupLogic.validationJoinGroup(talkRoomId,user.getUserId());
		
		//処理
		userInGroupLogic.delete(talkRoomId,user.getUserId());
	}
}