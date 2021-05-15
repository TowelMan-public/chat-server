package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.HaveUserEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.response.HaveUserResponse;
import com.example.demo.exception.AlreadyHaveUserException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotHaveUserException;
import com.example.demo.logic.DesireUserLogic;
import com.example.demo.logic.DialogueLogic;
import com.example.demo.logic.HaveUserLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;

/**
 * ユーザーが持っている友達に関するAPIのサービスロジック
 */
@Service
public class UserInDialogueService {
	@Autowired
	UserLogic userLogic;
	@Autowired
	HaveUserLogic haveUserLogic;
	@Autowired
	DesireUserLogic desireUserLogic;
	@Autowired
	DialogueLogic dialogueLogic;

	/**
	 * レスポンス向けの友達リストを取得
	 * @param user ユーザー情報
	 * @return レスポンス向けの友達リスト
	 */
	public List<HaveUserResponse> getUserInDiarogueList(UserDetailsImp user) {
		//データ取得・宣言
		List<HaveUserEntity> haveUserList = haveUserLogic.getHaveUserList(user.getUserId());
		List<HaveUserResponse> responselist = new ArrayList<>();
		
		//処理
		for(HaveUserEntity haveUserEntity : haveUserList) {
			UserEntity userEntity = 
					userLogic.getUserByUserIdNonThrow(haveUserEntity.getUserId());
			if(userEntity != null)
				responselist.add(new HaveUserResponse(haveUserEntity,userEntity));
		}
		
		return responselist;
	}

	/**
	 * 友達を追加する
	 * @param user ユーザー情報
	 * @param haveUserIdName 追加するユーザーID名
	 * @throws NotFoundException
	 * @throws AlreadyHaveUserException
	 * @throws NotHaveUserException
	 */
	public void insertUserInDiarogue(UserDetailsImp user, String haveUserIdName) throws NotFoundException, AlreadyHaveUserException, NotHaveUserException {
		//チェック・必要データ取得
		Integer haveUserId = userLogic.getUserByUserIdName(haveUserIdName)
									   .getUserId();
		haveUserLogic.validationNotInsertedOne(user.getUserId(),haveUserId);
		
		//他、処理に必要な変数宣言
		Integer dialogueTalkRoomId;
		Integer lastTalkIndex;
		
		//分岐処理
		if(haveUserLogic.isInsertedOne(haveUserId,user.getUserId())) {
			//相手が友達に追加している
			dialogueTalkRoomId = haveUserLogic.getHaveUser(haveUserId,user.getUserId())
											  .getTalkRoomId();
			lastTalkIndex = dialogueLogic.getDialogue(dialogueTalkRoomId)
										 .getLastTalkIndex();
			
			desireUserLogic.delete(user.getUserId(), haveUserId);
		}else {
			//相手も友達に追加してない
			lastTalkIndex = 0;
			dialogueTalkRoomId = dialogueLogic.createTalkRoom();
		}
		
		//処理
		haveUserLogic.insert(user.getUserId(),haveUserId,dialogueTalkRoomId,lastTalkIndex);
	}
	
	/**
	 * 友達を削除する
	 * @param user ユーザー情報
	 * @param haveUserIdName 削除する友達のユーザーID名
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	public void deleteUserInDiarogue(UserDetailsImp user, String haveUserIdName) throws NotFoundException, NotHaveUserException {
		//チェック・必要データ取得
		Integer haveUserId = userLogic.getUserByUserIdName(haveUserIdName)
									   .getUserId();
		haveUserLogic.validationInsertedOne(user.getUserId(),haveUserId);
		
		//処理
		desireUserLogic.delete(haveUserId, user.getUserId());
		haveUserLogic.delete(user.getUserId(), haveUserId);
	}
}