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
		//トークルーム作成・データ取得
		var parentEntity = new ParentTalkRoomEntity();
		parentTalkRoomEntityMapper.insertSelective(parentEntity);
		
		//トークルームを友達トークに登録
		var dialogueEntity = new DialogueTalkRoomEntity();
		dialogueEntity.setTalkRoomId(
				parentEntity.getTalkRoomId());
		dialogueEntity.setLastTalkIndex(0);
		dialogueEntity.setIsEnabled(true);
		
		dialogueTalkRoomEntityMapper.insert(dialogueEntity);
		
		return parentEntity.getTalkRoomId();
	}

	/**
	 * ユーザートークルームの取得
	 * @param dialogueTalkRoomId ユーザートークルーム
	 * @return ユーザートークルーム
	 * @throws NotFoundException 見つからない
	 */
	public DialogueTalkRoomEntity getDialogue(Integer dialogueTalkRoomId) throws NotFoundException {
		var entity = getDialogueNonThrow(dialogueTalkRoomId);
		if(entity == null)
			throw new NotFoundException("dialogueTalkRoomId");
		else
			return entity;
	}

	/**
	 *  ユーザートークルームの取得<br>
	 *  例外を投げない
	 * @param dialogueTalkRoomId ユーザートークルーム
	 * @return ユーザートークルーム。失敗ならnull
	 */
	public DialogueTalkRoomEntity getDialogueNonThrow(Integer dialogueTalkRoomId) {
		return dialogueTalkRoomEntityMapper.selectByPrimaryKey(dialogueTalkRoomId);
	}

	/**
	 * lastTalkIndexをインクリメントする
	 * @param dialogueTalkRoomId 友達トークルームID
	 */
	public void incrementLastTalkIndex(Integer dialogueTalkRoomId) {
		dialogueTalkRoomEntityMapper.incrementLastTalkIndex(dialogueTalkRoomId);
	}

}
