package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.GroupTalkRoomEntityExample;
import com.example.demo.entity.GroupTalkRoomEntity;
import com.example.demo.entity.ParentTalkRoomEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.GroupTalkRoomEntityMapper;
import com.example.demo.repository.ParentTalkRoomEntityMapper;

/**
 * グループ周りの共通処理クラス
 */
@Component
public class GroupLogic {

	@Autowired
	GroupTalkRoomEntityMapper groupTalkRoomEntityMapper;
	@Autowired
	ParentTalkRoomEntityMapper parentTalkRoomEntityMapper;
	
	/**
	 * グループが見つかるかのチェック<br>
	 * 失敗なら例外を投げる
	 * @param talkRoomId グループトークルームID
	 * @throws NotFoundException 見つからない
	 */
	public void validationIsFound(Integer talkRoomId) throws NotFoundException {
		if(isFound(talkRoomId))
			throw new NotFoundException("talkRoomId");
	}
	
	/**
	 * グループが見つかるかのチェック
	 * @param talkRoomId グループトークルームID
	 * @return 成功ならtrue、失敗ならfalse
	 */
	public boolean isFound(Integer talkRoomId) {
		//SQL作成
		var dto = new GroupTalkRoomEntityExample();
		dto
			.or()
				.andTalkRoomIdEqualTo(talkRoomId)
				.andIsEnabledEqualTo(true);
		
		//処理
		return groupTalkRoomEntityMapper.countByExample(dto) != 0;
	}

	/**
	 * グループを取得する
	 * @param talkRoomId グループトークルームID
	 * @return グループ
	 * @throws NotFoundException 見つからない
	 */
	public GroupTalkRoomEntity getGroup(Integer talkRoomId) throws NotFoundException {
		//SQL作成
		var dto = new GroupTalkRoomEntityExample();
		dto
			.or()
				.andTalkRoomIdEqualTo(talkRoomId)
				.andIsEnabledEqualTo(true);
		
		//処理
		List<GroupTalkRoomEntity> entityList = groupTalkRoomEntityMapper.selectByExample(dto);
		if(entityList.isEmpty())
			throw new NotFoundException("talkRoomId");
		else
			return entityList.get(0);
	}

	/**
	 * グループを取得する<br>
	 * 見つからなくても例外を投げない
	 * @param talkRoomId グループトークルームID
	 * @return グループ・見つからなかったらnull
	 */
	public GroupTalkRoomEntity getGroupNonThrows(Integer talkRoomId) {
		//SQL作成
		var dto = new GroupTalkRoomEntityExample();
		dto
			.or()
				.andTalkRoomIdEqualTo(talkRoomId)
				.andIsEnabledEqualTo(true);
		
		//処理
		List<GroupTalkRoomEntity> entityList = groupTalkRoomEntityMapper.selectByExample(dto);
		if(entityList.isEmpty())
			return null;
		else
			return entityList.get(0);
	}

	/**
	 * グループ名の更新
	 * @param talkRoomId グループトークルームID
	 * @param groupName グループ名
	 */
	public void updateGroupName(Integer talkRoomId, String groupName) {
		//データ作成
		var entity = new GroupTalkRoomEntity();
		entity.setTalkRoomId(talkRoomId);
		entity.setGroupName(groupName);
		
		//処理
		groupTalkRoomEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * グループの削除
	 * @param talkRoomId グループトークルームID
	 */
	public void delete(Integer talkRoomId) {
		//データ作成
		var entity = new GroupTalkRoomEntity();
		entity.setTalkRoomId(talkRoomId);
		entity.setIsEnabled(false);
		
		//処理
		groupTalkRoomEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * グループ作成
	 * @param groupName グループ名
	 * @return 新しいグループトークルームID
	 */
	public Integer insert(String groupName) {
		//トークルームIDの作成・取得
		var parentEntity = new ParentTalkRoomEntity();
		parentTalkRoomEntityMapper.insertSelective(parentEntity);
		
		//データ作成
		var groupEntity = new GroupTalkRoomEntity();
		groupEntity.setTalkRoomId(
				parentEntity.getTalkRoomId());
		groupEntity.setGroupName(groupName);
		groupEntity.setLastTalkIndex(0);
		
		//処理
		groupTalkRoomEntityMapper.insertSelective(groupEntity);
		return parentEntity.getTalkRoomId();
	}
}