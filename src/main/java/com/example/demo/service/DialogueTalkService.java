package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.TalkEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.BadRequestFormException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotHaveUserException;
import com.example.demo.logic.DialogueLogic;
import com.example.demo.logic.HaveUserLogic;
import com.example.demo.logic.TalkLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 友達トーク単体に関するAPIのサービスロジック
 */
@Service
public class DialogueTalkService {
	@Autowired
	UserLogic userLogic;
	@Autowired
	TalkLogic talkLogic;
	@Autowired
	HaveUserLogic haveUserLogic;
	@Autowired
	DialogueLogic dialogueLogic;
	
	/**
	 * 友達トークを作成する
	 * @param user ユーザー情報
	 * @param haveUserIdName 友達登録しているユーザーのID名
	 * @param talkContentText 内容
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void insertTalk(UserDetailsImp user, String haveUserIdName, String talkContentText) 
			throws NotFoundException, NotHaveUserException {
		//チェック・必要データ取得
		BindHaveUserAndTalkRom bindHaveUserAndTalkRom = getTalkRoomIdAndValidationTalkRoom(user.getUserId(), haveUserIdName);
		Integer talkRoomLastTalkIndex = dialogueLogic.getDialogueNonThrow(bindHaveUserAndTalkRom.getTalkRoomId())
													 .getLastTalkIndex();
		
		//処理
		talkLogic.insert(bindHaveUserAndTalkRom.getTalkRoomId(), user.getUserId(), talkContentText, talkRoomLastTalkIndex + 1);
		
		//後処理
		dialogueLogic.incrementLastTalkIndex(bindHaveUserAndTalkRom.getTalkRoomId());
		haveUserLogic.updateLastTalkIndex(user.getUserId(), bindHaveUserAndTalkRom.getHaveUserId(), talkRoomLastTalkIndex + 1);
	}

	/**
	 * 友達トークを取得する
	 * @param user ユーザー情報
	 * @param haveUserIdName 友達登録しているユーザーのID名
	 * @param talkIndex トークインデックス
	 * @return レスポンス向けのトーク
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	public TalkResponse getTalk(UserDetailsImp user, String haveUserIdName, Integer talkIndex) 
			throws NotFoundException, NotHaveUserException {
		//チェック・必要データ取得
		BindHaveUserAndTalkRom bindHaveUserAndTalkRom = getTalkRoomIdAndValidationTalkRoom(user.getUserId(), haveUserIdName);
		talkLogic.validationIsFound(bindHaveUserAndTalkRom.getTalkRoomId(), talkIndex);
		
		//処理
		TalkEntity talkEntity = talkLogic.getTalk(bindHaveUserAndTalkRom.getTalkRoomId(), talkIndex);
		UserEntity userEntity = userLogic.getUserByUserId(talkEntity.getUserId());
		return new TalkResponse(talkEntity, userEntity);
	}

	/**
	 * 友達トークを更新する
	 * @param user ユーザー情報
	 * @param haveUserIdName 友達登録しているユーザーのID名
	 * @param talkIndex トークインデックス
	 * @param talkContentText 内容
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 * @throws BadRequestFormException
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void updateTalk(UserDetailsImp user, String haveUserIdName, Integer talkIndex, String talkContentText) 
			throws NotFoundException, NotHaveUserException, BadRequestFormException {
		//チェック・必要データ取得
		BindHaveUserAndTalkRom bindHaveUserAndTalkRom = getTalkRoomIdAndValidationTalkRoom(user.getUserId(), haveUserIdName);
		talkLogic.validationIsEnabled(bindHaveUserAndTalkRom.getTalkRoomId(), talkIndex, user.getUserId());
		
		//処理
		talkLogic.update(bindHaveUserAndTalkRom.getTalkRoomId(), talkIndex, talkContentText);
	}

	/**
	 * 友達トークの削除
	 * @param user ユーザー情報
	 * @param haveUserIdName 友達登録しているユーザーのID名
	 *@param talkIndex トークインデックス
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 * @throws BadRequestFormException
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void deleteTalk(UserDetailsImp user, String haveUserIdName, Integer talkIndex)
			throws NotFoundException, NotHaveUserException, BadRequestFormException {
		//チェック・必要データ取得
		BindHaveUserAndTalkRom bindHaveUserAndTalkRom = getTalkRoomIdAndValidationTalkRoom(user.getUserId(), haveUserIdName);
		talkLogic.validationIsEnabled(bindHaveUserAndTalkRom.getTalkRoomId(), talkIndex, bindHaveUserAndTalkRom.getHaveUserId());
		
		//処理
		talkLogic.delete(bindHaveUserAndTalkRom.getTalkRoomId(), talkIndex);
	}
	
	/**
	 * userIdに指定されたユーザーがhaveUserIdNameに指定されたユーザーを友達として登録していることを検証して、<br>
	 * haveUserIdNameに指定されたユーザーのIDと対応する友達トークルームIDを返す。
	 * @param userId ユーザーID
	 * @param haveUserIdName 友達登録しているユーザーのID名
	 * @return haveUserIdとtalkRoomIdをセットで扱うためのインナークラス
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	private BindHaveUserAndTalkRom getTalkRoomIdAndValidationTalkRoom(Integer userId, String haveUserIdName)
			throws NotFoundException, NotHaveUserException {
		Integer haveUserId = userLogic.getUserByUserIdName(haveUserIdName)
									  .getUserId();
		Integer talkRoomId = haveUserLogic.getHaveUser(userId, haveUserId)
							.getTalkRoomId();
		
		return new BindHaveUserAndTalkRom(haveUserId,talkRoomId);
	}
	
	/**
	 * haveUserIdとtalkRoomIdをセットで扱うためのインナークラス
	 */
	@Data
	@AllArgsConstructor
	private class BindHaveUserAndTalkRom{
		private Integer haveUserId;
		private Integer talkRoomId;
	}
}
