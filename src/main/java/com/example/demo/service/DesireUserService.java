package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.DesireHaveUserEntity;
import com.example.demo.entity.response.DesireHaveUserResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.logic.DesireUserLogic;
import com.example.demo.logic.HaveUserLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.security.UserDetailsImp;

/**
 * 友達追加申請に関するAPIのサービスロジック
 */
@Service
public class DesireUserService {

	@Autowired
	UserLogic userLogic;
	@Autowired
	HaveUserLogic haveUserLogic;
	@Autowired
	DesireUserLogic desireUserLogic;
	
	/**
	 * 友達追加申請リストの取得
	 * @param user ユーザー情報
	 * @return レスポンス用の友達追加申請リスト
	 */
	public List<DesireHaveUserResponse> getDesireUser(UserDetailsImp user) {
		//データ取得・宣言
		List<DesireHaveUserEntity> desireEntityList = desireUserLogic.getDesireUserList(user.getUserId());
		List<DesireHaveUserResponse> responceEntityList = new ArrayList<>();
		
		//処理
		for(DesireHaveUserEntity desireEntity: desireEntityList) {
			var userEntity = userLogic.getUserByUserIdNonThrow(desireEntity.getHaveUserId());
			if(userEntity != null)
				responceEntityList.add(new DesireHaveUserResponse(desireEntity, userEntity));
		}
		
		return responceEntityList;
	}

	/**
	 * 友達追加申請を断る（削除する）
	 * @param user ユーザー情報
	 * @param haveUserIdName 友達申請をしているユーザーID名
	 * @throws NotFoundException 見つからない
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void deleteDesireUser(UserDetailsImp user, String haveUserIdName) throws NotFoundException {
		//チェック
		var userEntity = userLogic.getUserByUserIdName(haveUserIdName);
		desireUserLogic.validationIsFound(user.getUserId(), userEntity.getUserId());
		
		//処理
		desireUserLogic.delete(user.getUserId(), userEntity.getUserId());
	}

	/**
	 * 友達申請を受ける（友達追加をする）
	 * @param user ユーザー情報
	 * @param haveUserIdName 友達申請をしているユーザーID名
	 * @throws NotFoundException 見つからない
	 */
	@Transactional(rollbackForClassName = "Exception")
	public void joinUser(UserDetailsImp user, String haveUserIdName) throws NotFoundException {
		//チェック
		var userEntity = userLogic.getUserByUserIdName(haveUserIdName);
		desireUserLogic.validationIsFound(user.getUserId(), userEntity.getUserId());
		//処理
		var desireEntity = desireUserLogic.getDesireUser(user.getUserId(), userEntity.getUserId());
		desireUserLogic.delete(user.getUserId(), userEntity.getUserId());
		haveUserLogic.join(desireEntity);
	}

}
