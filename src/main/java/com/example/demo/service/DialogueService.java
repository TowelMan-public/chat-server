package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.DesireHaveUserEntity;
import com.example.demo.entity.HaveUserEntity;
import com.example.demo.entity.TalkEntity;
import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotHaveUserException;
import com.example.demo.logic.DesireUserLogic;
import com.example.demo.logic.DialogueLogic;
import com.example.demo.logic.HaveUserLogic;
import com.example.demo.logic.Talklogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.utility.CommonUtility;

/**
 * 友達トークに関するAPIのサービスロジック
 */
@Service
public class DialogueService {
	@Autowired
	UserLogic userLogic;
	@Autowired
	HaveUserLogic haveUserLogic;
	@Autowired
	DesireUserLogic desireUserLogic;
	@Autowired
	DialogueLogic dialogueLogic;
	@Autowired
	Talklogic talklogic;
	@Autowired
	CommonUtility commonUtility;

	/**
	 * レスポンス向けのトークリストを取得する
	 * @param user ユーザー情報
	 * @param haveUserIdName 友達のユーザーId名
	 * @param startIndex 取得するトークの始まりのインデックス
	 * @param maxSize 最大で取得するトークの件数
	 * @return レスポンス向けのトークリスト
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	@Transactional(rollbackForClassName = "Exception")
	public List<TalkResponse> getDiarogueTalks(UserDetailsImp user, String haveUserIdName, Integer startIndex, Integer maxSize)
			throws NotFoundException, NotHaveUserException {
		//チェック・必要データ取得
		Integer haveUserId = userLogic.getUserByUserIdName(haveUserIdName)
									  .getUserId();
		Integer diarogueTalkRoomId;
		HaveUserEntity haveUserEntity = haveUserLogic.getHaveUserNonThrow(user.getUserId(), haveUserId);
		DesireHaveUserEntity desireHaveUserEntity = desireUserLogic.getDesireUserNonThorw(user.getUserId(), haveUserId);
		if(haveUserEntity != null)
			diarogueTalkRoomId = haveUserEntity.getTalkRoomId();
		else if(desireHaveUserEntity != null)
			diarogueTalkRoomId = desireHaveUserEntity.getTalkRoomId();
		else
			throw new NotHaveUserException();
		
		//データ取得・宣言
		List<TalkEntity> talkList = talklogic.getTalks(diarogueTalkRoomId, startIndex, maxSize);
		List<TalkResponse> responseList = new ArrayList<>();
		
		//処理
		for(TalkEntity talkEntity : talkList) {
			var userEntity =
					userLogic.getUserByUserIdNonThrow(talkEntity.getUserId());
			if(userEntity != null)
				responseList.add(new TalkResponse(talkEntity,userEntity));
		}
		
		//後処理
		Integer userLastTalkIndex = startIndex + maxSize - 1;
		Integer talkRoomLastTalkIndex = dialogueLogic.getDialogue(diarogueTalkRoomId)
							 						 .getLastTalkIndex();
		if(haveUserEntity != null)
			haveUserLogic.updateLastTalkIndex(user.getUserId(), haveUserId, commonUtility.min(userLastTalkIndex, talkRoomLastTalkIndex));
		else
			desireUserLogic.updateLastTalkIndex(user.getUserId(), haveUserId, commonUtility.min(userLastTalkIndex, talkRoomLastTalkIndex));
		
		return responseList;
	}
}
