package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.DesireUserInGroupEntity;
import com.example.demo.entity.GroupTalkRoomEntity;
import com.example.demo.entity.response.DesireUserInGroupResponce;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotInsertedGroupDesireException;
import com.example.demo.logic.DesireUserInGroupLogic;
import com.example.demo.logic.GroupLogic;
import com.example.demo.logic.UserInGroupLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;

/**
 * グループ加入してほしい申請に関するAPIのサービスロジック
 */
@Service
public class DesireGroupService {
	@Autowired
	UserLogic userLogic;
	@Autowired
	DesireUserInGroupLogic desireUserInGroupLogic;
	@Autowired
	UserInGroupLogic userInGroupLogic;
	@Autowired
	GroupLogic groupLogic;

	
	/**
	 * グループに加入してほしい申請リストの取得
	 * @param user ユーザー情報
	 * @return レスポンス用のグループに加入してほしい申請リスト
	 */
	public List<DesireUserInGroupResponce> getDesireGroup(UserDetailsImp user) {
		//データ取得・宣言
		List<DesireUserInGroupEntity> desireEntityList = desireUserInGroupLogic.getDesireGroupList(user.getUserId());
		List<DesireUserInGroupResponce> responceEntityList = new ArrayList<>();
		
		//処理
		for(DesireUserInGroupEntity desireEntity: desireEntityList) {
			GroupTalkRoomEntity groupEntity =
					groupLogic.getGroupNonThrows(desireEntity.getTalkRoomId());
			if(groupEntity != null)
				responceEntityList.add(new DesireUserInGroupResponce(desireEntity, groupEntity));
		}
		
		return responceEntityList;
	}

	/**
	 * グループに加入してほしい申請のお断り（削除）
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームID
	 * @throws NotFoundException 見つからなかったものがある
	 * @throws NotInsertedGroupDesireException 申請出てない
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void deleteDesireGroup(UserDetailsImp user, Integer talkRoomId) throws NotFoundException, NotInsertedGroupDesireException {
		//バリデーションチェック
		groupLogic.validationIsFound(talkRoomId);
		desireUserInGroupLogic.validationInserted(talkRoomId,user.getUserId());
		
		//処理
		desireUserInGroupLogic.delete(user.getUserId(),talkRoomId);
	}

	/**
	 * グループに加入してほしい申請を受け入れる（グループに加入する）
	 * @param user ユーザー情報
	 * @param talkRoomId グループトークルームID
	 * @throws NotFoundException 見つからなかったものがある
	 * @throws NotInsertedGroupDesireException 申請出てない
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void joinGroup(UserDetailsImp user, Integer talkRoomId) throws NotFoundException, NotInsertedGroupDesireException {
		//バリデーションチェック
		groupLogic.validationIsFound(talkRoomId);
		desireUserInGroupLogic.validationInserted(talkRoomId,user.getUserId());
		
		DesireUserInGroupEntity desireEntity = desireUserInGroupLogic.getDesireGroup(user.getUserId(), talkRoomId);
		desireUserInGroupLogic.delete(user.getUserId(),talkRoomId);
		userInGroupLogic.joinGroup(desireEntity);
	}
	
}
