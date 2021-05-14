package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.GroupTalkRoomEntityExample;
import com.example.demo.entity.GroupTalkRoomEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.GroupTalkRoomEntityMapper;

/**
 * グループ周りの共通処理クラス
 */
@Component
public class GroupLogic {

	@Autowired
	GroupTalkRoomEntityMapper groupTalkRoomEntityMapper;
	
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
}