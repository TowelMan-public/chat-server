package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.GroupTalkRoomEntity;
import com.example.demo.entity.TalkEntity;
import com.example.demo.entity.UserInGroupEntity;
import com.example.demo.entity.response.GroupTalkRoomResponse;
import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotJoinGroupException;
import com.example.demo.logic.DesireUserInGroupLogic;
import com.example.demo.logic.GroupLogic;
import com.example.demo.logic.TalkLogic;
import com.example.demo.logic.UserInGroupLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.utility.CommonUtility;

/**
 * グループトークに関するAPIのサービスロジック
 */
@Service
public class GroupService {
	@Autowired
	UserLogic userLogic;
	@Autowired
	UserInGroupLogic userInGroupLogic;
	@Autowired
	GroupLogic groupLogic;
	@Autowired
	DesireUserInGroupLogic desireUserInGroupLogic;
	@Autowired
	TalkLogic talkLogic;
	@Autowired
	CommonUtility commonUtility;
	
	/**
	 * グループの取得
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームID
	 * @return グループ
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 */
	public GroupTalkRoomEntity getGroup(UserDetailsImp user, Integer talkRoomId) throws NotJoinGroupException, NotFoundException {
		//チェック
		groupLogic.validationIsFound(talkRoomId);
		userInGroupLogic.validationJoinGroup(talkRoomId, user.getUserId());
		
		//処理
		return groupLogic.getGroupNonThrows(talkRoomId);
	}
	
	/**
	 * 自分が加入してるグループリストの取得
	 * @param user ユーザー情報
	 * @return レスポンス向けグループリスト
	 */
	public List<GroupTalkRoomResponse> getGroups(UserDetailsImp user) {
		//データ取得・宣言
		List<UserInGroupEntity> groupTalkRoomList = userInGroupLogic.getGroupList(user.getUserId());
		List<GroupTalkRoomResponse> resonseList = new ArrayList<>();
		
		//処理
		for(UserInGroupEntity userInGroupEntity : groupTalkRoomList) {
			GroupTalkRoomEntity groupEntity =
					groupLogic.getGroupNonThrows(userInGroupEntity.getTalkRoomId());			
			if(groupEntity != null)
				resonseList.add(new GroupTalkRoomResponse(groupEntity, userInGroupEntity));
		}
		
		return resonseList;
	}

	/**
	 * グループ名の更新
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームID
	 * @param groupName グループ名
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void updateGroupName(UserDetailsImp user, Integer talkRoomId, String groupName) throws NotJoinGroupException, NotFoundException {
		//チェック
		groupLogic.validationIsFound(talkRoomId);
		userInGroupLogic.validationJoinGroup(talkRoomId, user.getUserId());
		
		//処理
		groupLogic.updateGroupName(talkRoomId, groupName);
		
	}

	/**
	 * グループトークリストの取得
	 * @param user ユーザー情報
	 * @param talkRoomId トークルームID
	 * @param startIndex 始めの位置のトークインデックス
	 * @param maxSize 最大件数
	 * @return レスポンス向けのグループトークリスト
	 * @throws NotFoundException
	 * @throws NotJoinGroupException
	 */
	@Transactional(rollbackForClassName = "Exception")
	public List<TalkResponse> getGroupTalks(UserDetailsImp user, Integer talkRoomId, Integer startIndex, Integer maxSize)
			throws NotFoundException, NotJoinGroupException {		
		//チェック
		groupLogic.validationIsFound(talkRoomId);
		boolean isInGroup = userInGroupLogic.isJoinGroup(talkRoomId, user.getUserId());
		if(!(isInGroup ||
				desireUserInGroupLogic.isInserted(talkRoomId, user.getUserId())))
			throw new NotJoinGroupException();
		
		//データ取得・宣言
		List<TalkEntity> talkList = talkLogic.getTalks(talkRoomId, startIndex, maxSize);
		List<TalkResponse> responseList = new ArrayList<>();
		
		//処理
		for(TalkEntity talkEntity : talkList) {
			var userEntity =
					userLogic.getUserByUserIdNonThrow(talkEntity.getUserId());
			if(userEntity != null)
				responseList.add(new TalkResponse(talkEntity,userEntity));
		}
		
		//後処理（LastTalkIndexの更新）
		Integer userLastTalkIndex = startIndex + maxSize - 1;
		Integer talkRoomLastTalkIndex = groupLogic.getGroupNonThrows(talkRoomId)
												  .getLastTalkIndex();
		if(isInGroup)
			userInGroupLogic.updateLastTalkIndex(user.getUserId(), talkRoomId, commonUtility.min(userLastTalkIndex, talkRoomLastTalkIndex));
		else
			desireUserInGroupLogic.updateLastTalkIndex(user.getUserId(), talkRoomId, commonUtility.min(userLastTalkIndex, talkRoomLastTalkIndex));
		return responseList;
	}

	/**
	 * グループの削除
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームID
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void deleteGroup(UserDetailsImp user, Integer talkRoomId) throws NotJoinGroupException, NotFoundException {
		//チェック
		groupLogic.validationIsFound(talkRoomId);
		userInGroupLogic.validationJoinGroup(talkRoomId, user.getUserId());
		
		//処理
		groupLogic.delete(talkRoomId);
	}

	/**
	 * グループ作成
	 * @param user ユーザー情報
	 * @param groupName グループ名
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void insertGroup(UserDetailsImp user, String groupName) {
		//処理
		Integer talkRoomId = groupLogic.insert(groupName);
		userInGroupLogic.joinGroup(talkRoomId, user.getUserId(), 0);
	}
}
