package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.DeletedUserEntityExample;
import com.example.demo.dto.UserEntityExample;
import com.example.demo.entity.DeletedUserEntity;
import com.example.demo.entity.ParentUserEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnEnableException;
import com.example.demo.form.UserForm;
import com.example.demo.repository.DeletedUserEntityMapper;
import com.example.demo.repository.ParentUserEntityMapper;
import com.example.demo.repository.UserEntityMapper;
import com.example.demo.security.UserDetailsImp;

/**
 * ユーザーアカウント周りの共通処理クラス
 */
@Component
public class UserLogic {

	@Autowired
	ParentUserEntityMapper parentUserEntityMapper;
	@Autowired
	UserEntityMapper userEntityMapper;
	@Autowired
	DeletedUserEntityMapper deletedUserEntityMapper;
	
	/**
	 * ユーザーId名からユーザ情報の取得
	 * @param userIdName　ユーザーId名
	 * @return ユーザ情報
	 * @throws com.example.demo.exception.NotFoundException ユーザーIdが存在しない
	 * @throws com.example.demo.exception.UnEnableException ユーザーIdが無効なものである
	*/
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

	/**
	 * ユーザーIdからユーザ情報の取得
	 * @param userId　ユーザーId
	 * @return ユーザ情報
	 * @throws com.example.demo.exception.NotFoundException ユーザーIdが存在しない
	 * @throws com.example.demo.exception.UnEnableException ユーザーIdが無効なものである
	*/
	public UserEntity getUserByUserId(Integer userId) throws UnEnableException, NotFoundException {
		UserEntity entity = userEntityMapper.selectByPrimaryKey(userId);
		
		if(entity != null) {
			validationIsEnable(entity.getUserId());
			return entity;
		}else {
			throw new NotFoundException("userId");
		}
	}
	
	/**
	 * ユーザーIdが実際に存在してるユーザーのもので、さらに有効なものであるかのチェック<br>
	 * チェックでひっかかっから例外が投げられる。
	 * @param userId　ユーザーId
	 * @throws com.example.demo.exception.NotFoundException ユーザーIdが存在しない
	 * @throws com.example.demo.exception.UnEnableException ユーザーIdが無効なものである
	*/
	public void validationIsFound(Integer userId) throws UnEnableException, NotFoundException {
		UserEntity entity = userEntityMapper.selectByPrimaryKey(userId);
		
		if(entity != null && entity.getUserId() != null) {
			validationIsEnable(userId);
		}else {
			throw new NotFoundException("userId");
		}
	}
	
	/**
	 * ユーザーIdが実際に存在してるユーザーのもので、さらに有効なものであるかのチェック
	 * @param userId ユーザーId
	 * @return 成功ならtrue、失敗ならfalse
	 */
	public boolean isFound(Integer userId){
		UserEntity entity = userEntityMapper.selectByPrimaryKey(userId);
		
		if(entity != null && entity.getUserId() != null) {
			return isEnable(userId);
		}else {
			return false;
		}
	}
	
	/**
	 * ユーザーIdが有効なものであるかのチェック
	 * @param userId　ユーザーId
	 * @return ユーザ情報
	 * @throws com.example.demo.exception.UnEnableException ユーザーIdが無効なものである
	*/
	public void validationIsEnable(Integer userId) throws UnEnableException {
		DeletedUserEntityExample dto = new DeletedUserEntityExample();
		dto
			.or()
			.andUserIdEqualTo(userId);
		
		if(deletedUserEntityMapper.countByExample(dto) != 0)
			throw new UnEnableException("UserEntity");
	}
	
	/**
	 * ユーザーIdが有効なものであるかのチェック
	 * @param userId ユーザーId
	 * @return 成功ならtrue、失敗ならfalse
	 */
	public boolean isEnable(Integer userId){
		DeletedUserEntityExample dto = new DeletedUserEntityExample();
		dto
			.or()
			.andUserIdEqualTo(userId);
		
		return deletedUserEntityMapper.countByExample(dto) != 0;
	}

	/**
	 * ユーザーを追加する
	 * @param form 追加するユーザー情報
	 */
	public void insertUser(UserForm form) {
		//userIdの取得
		ParentUserEntity parentEntity = new ParentUserEntity();
		parentUserEntityMapper.insertSelective(parentEntity);
		
		//データ作成
		UserEntity insertEntity = new UserEntity();
		insertEntity.setUserId(
				parentEntity.getUserId());
		insertEntity.setUserIdName(
				form.getUserIdName());
		insertEntity.setUserName(
				form.getUserName());
		insertEntity.setPassword(
				form.getPassword());
		
		//実行
		userEntityMapper.insert(insertEntity);
	}

	/**
	 * ユーザーID名を変更する
	 * @param user アクセスしたユーザーの情報
	 * @param userIdName ユーザーID名
	 */
	public void updateUserIdName(UserDetailsImp user, String userIdName) {
		//データ作成
		UserEntity insertEntity = new UserEntity();
		insertEntity.setUserId(
				user.getUserId());
		insertEntity.setUserIdName(userIdName);
		
		//実装
		userEntityMapper.updateByPrimaryKeySelective(insertEntity);
	}

	/**
	 * ユーザー名を変更する
	 * @param user アクセスしたユーザーの情報
	 * @param userName ユーザー名
	 */
	public void updateUserName(UserDetailsImp user, String userName) {
		//データ作成
		UserEntity insertEntity = new UserEntity();
		insertEntity.setUserId(
				user.getUserId());
		insertEntity.setUserName(userName);
		
		//実装
		userEntityMapper.updateByPrimaryKeySelective(insertEntity);
	}

	/**
	 * パスワードの変更
	 * @param user アクセスしたユーザーの情報
	 * @param password 暗号化されたパスワード
	 */
	public void updatePassword(UserDetailsImp user, String password) {
		//データ作成
		UserEntity insertEntity = new UserEntity();
		insertEntity.setUserId(
				user.getUserId());
		insertEntity.setPassword(password);
		
		//実装
		userEntityMapper.updateByPrimaryKeySelective(insertEntity);
	}

	/**
	 * ユーザーを削除する（物理的には残ってたりする）
	 * @param user アクセスしたユーザーの情報
	 */
	public void deleteUser(UserDetailsImp user) {
		userEntityMapper.deleteByPrimaryKey(user.getUserId());
		if(isEnable(user.getUserId())) {
			DeletedUserEntity entity = new DeletedUserEntity();
			entity.setUserId(user.getUserId());
			deletedUserEntityMapper.insert(entity);
		}
	}
}
