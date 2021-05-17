package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TalkEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.BadRequestFormException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotJoinGroupException;
import com.example.demo.logic.GroupLogic;
import com.example.demo.logic.Talklogic;
import com.example.demo.logic.UserInGroupLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;

/**
 * 友達トーク単体に関するAPIのサービスロジック
 */
@Service
public class GroupTalkService {
	@Autowired
	UserLogic userLogic;
	@Autowired
	Talklogic talklogic;
	@Autowired
	GroupLogic groupLogic;
	@Autowired
	UserInGroupLogic userInGroupLogic;
	
	/**
	 * グループトークを作成する
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームID
	 * @param talkContentText 内容
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 */
	public void insertTalk(UserDetailsImp user, Integer talkRoomId, String talkContentText)
			throws NotJoinGroupException, NotFoundException {
		//チェック・必要データ習得
		validationTalkRoom(user.getUserId(), talkRoomId);
		Integer talkRoomLastTalkIndex = groupLogic.getGroup(talkRoomId)
												  .getLastTalkIndex();
		
		//処理
		talklogic.insert(talkRoomId, user.getUserId(), talkContentText, talkRoomLastTalkIndex + 1);
		
		//後処理
		groupLogic.incrementLastTalkIndex(talkRoomId);
		userInGroupLogic.updateLastTalkIndex(user.getUserId(), talkRoomId, talkRoomLastTalkIndex + 1);
	}
	
	/**
	 * グループトーク単体を取得する
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームID
	 * @param talkIndex トークインデックス
	 * @return レスポンス向けのグループトーク単体
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 */
	public TalkResponse getTalk(UserDetailsImp user, Integer talkRoomId, Integer talkIndex)
			throws NotJoinGroupException, NotFoundException {
		//チェック
		validationTalkRoom(user.getUserId(), talkRoomId);
		talklogic.validationIsFound(talkRoomId, talkIndex);
		
		//処理
		TalkEntity talkEntity = talklogic.getTalk(talkRoomId, talkIndex);
		UserEntity userEntity = userLogic.getUserByUserIdNonThrow(talkEntity.getUserId());
		return new TalkResponse(talkEntity, userEntity);
	}
	
	/**
	 * グループトークを更新する
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームID
	 * @param talkIndex トークインデックス
	 * @param talkContentText 内容
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 * @throws BadRequestFormException
	 */
	public void updateTalk(UserDetailsImp user, Integer talkRoomId, Integer talkIndex, String talkContentText)
			throws NotJoinGroupException, NotFoundException, BadRequestFormException {
		//チェック
		validationTalkRoom(user.getUserId(), talkRoomId);
		talklogic.validationIsEnabled(talkRoomId, talkIndex, user.getUserId());
		
		//処理
		talklogic.update(talkRoomId, talkIndex, talkContentText);
	}
	
	/**
	 * グループトークを削除する
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームID
	 * @param talkIndex トークインデックス
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 * @throws BadRequestFormException
	 */
	public void deleteTalk(UserDetailsImp user, Integer talkRoomId, Integer talkIndex) 
			throws NotJoinGroupException, NotFoundException, BadRequestFormException {
		//チェック
		validationTalkRoom(user.getUserId(), talkRoomId);
		talklogic.validationIsEnabled(talkRoomId, talkIndex, user.getUserId());
		
		//処理
		talklogic.delete(talkRoomId, talkIndex);
	}
	
	/**
	 * 指定されたユーザーがきちんと存在するグループに加入してるかを検証する。
	 * @param userId ユーザーID
	 * @param talkRoomId グループトークルームID
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 */
	private void validationTalkRoom(Integer userId, Integer talkRoomId)
			throws NotJoinGroupException, NotFoundException {
		groupLogic.validationIsFound(talkRoomId);
		userInGroupLogic.validationJoinGroup(talkRoomId, userId);
	}
}
