package com.example.demo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.DialogueTalkRoomEntity;
import com.example.demo.entity.ParentTalkRoomEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.DialogueTalkRoomEntityMapper;
import com.example.demo.repository.ParentTalkRoomEntityMapper;

/**
 * 友達トーク周りの共通処理クラス
 */
@Component
public class DialogueLogic {

	@Autowired
	DialogueTalkRoomEntityMapper dialogueTalkRoomEntityMapper;
	@Autowired
	ParentTalkRoomEntityMapper parentTalkRoomEntityMapper;
	
	/**
	 * トークルームを生成する
	 * @return トークルームID
	 */
	public Integer createTalkRoom() {
		var entity = new ParentTalkRoomEntity();
		parentTalkRoomEntityMapper.insertSelective(entity);
		
		return entity.getTalkRoomId();
	}

	/**
	 * ユーザートークルームの取得
	 * @param dialogueTalkRoomId ユーザートークルーム
	 * @return ユーザートークルーム
	 * @throws NotFoundException 見つからない
	 */
	public DialogueTalkRoomEntity getDialogue(Integer dialogueTalkRoomId) throws NotFoundException {
		var entity = dialogueTalkRoomEntityMapper.selectByPrimaryKey(dialogueTalkRoomId);
		if(entity == null)
			throw new NotFoundException("dialogueTalkRoomId");
		else
			return entity;
	}

	public DialogueTalkRoomEntity getDialogueNonThrow(Integer dialogueTalkRoomId) {
		return dialogueTalkRoomEntityMapper.selectByPrimaryKey(dialogueTalkRoomId);
	}

}
