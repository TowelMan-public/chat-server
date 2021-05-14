package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UserEntityExample;
import com.example.demo.entity.ParentUserEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.form.UserForm;
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
	
	/**
	 * ユーザーId名からユーザ情報の取得
	 * @param userIdName　ユーザーId名
	 * @return ユーザ情報
	 * @throws com.example.demo.exception.NotFoundException ユーザーIdが存在しない
	*/
	public UserEntity getUserByUserIdName(String userIdName) throws NotFoundException{
		var dto = new UserEntityExample();
		dto
			.or()
			.andUserIdNameEqualTo(userIdName)
			.andIsEnabledEqualTo(true);
		
		List<UserEntity> list = userEntityMapper.selectByExample(dto);
		if(list.isEmpty()) {
			throw new NotFoundException("userIdName");
		}else {
			return list.get(0);
		}
	}

	/**
	 * ユーザーIdからユーザ情報の取得
	 * @param userId　ユーザーId
	 * @return ユーザ情報
	 * @throws com.example.demo.exception.NotFoundException ユーザーIdが存在しない
	*/
	public UserEntity getUserByUserId(Integer userId) throws NotFoundException {
		var dto = new UserEntityExample();
		dto
			.or()
			.andUserIdEqualTo(userId)
			.andIsEnabledEqualTo(true);
		
		List<UserEntity> list = userEntityMapper.selectByExample(dto);
		if(list.isEmpty()) {
			throw new NotFoundException("userId");
		}else {
			return list.get(0);
		}
	}
	
	/**
	 * ユーザーIdからユーザ情報の取得<br>
	 * 例外を投げない
	 * @param userId　ユーザーId
	 * @return ユーザ情報。見つからなかったらnull。
	*/
	public UserEntity getUserByUserIdNonThrow(Integer userId) {
		var dto = new UserEntityExample();
		dto
			.or()
			.andUserIdEqualTo(userId)
			.andIsEnabledEqualTo(true);
		
		List<UserEntity> list = userEntityMapper.selectByExample(dto);
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}
	
	/**
	 * ユーザーIdが実際に存在してるユーザーのもので、さらに有効なものであるかのチェック<br>
	 * チェックでひっかかっから例外が投げられる。
	 * @param userId　ユーザーId
	 * @throws com.example.demo.exception.NotFoundException ユーザーIdが存在しない
	*/
	public void validationIsFound(Integer userId) throws NotFoundException {		
		if(!isFound(userId))
			throw new NotFoundException("userId");
	}
	
	/**
	 * ユーザーIdが実際に存在してるユーザーのもので、さらに有効なものであるかのチェック
	 * @param userId ユーザーId
	 * @return 成功ならtrue、失敗ならfalse
	 */
	public boolean isFound(Integer userId){
		var dto = new UserEntityExample();
		dto
			.or()
			.andUserIdEqualTo(userId)
			.andIsEnabledEqualTo(true);
		
		return userEntityMapper.countByExample(dto) != 0;
	}

	/**
	 * ユーザーを追加する
	 * @param form 追加するユーザー情報
	 */
	public void insertUser(UserForm form) {
		//userIdの取得
		var parentEntity = new ParentUserEntity();
		parentUserEntityMapper.insertSelective(parentEntity);
		
		//データ作成
		var insertEntity = new UserEntity();
		insertEntity.setUserId(
				parentEntity.getUserId());
		insertEntity.setUserIdName(
				form.getUserIdName());
		insertEntity.setUserName(
				form.getUserName());
		insertEntity.setPassword(
				form.getPassword());
		insertEntity.setIsEnabled(true);
		
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
		var insertEntity = new UserEntity();
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
		var insertEntity = new UserEntity();
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
		var insertEntity = new UserEntity();
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
	public void deleteUser(Integer userId) {
		var entity = new UserEntity();
		entity.setUserId(userId);
		entity.setIsEnabled(false);
	}
}