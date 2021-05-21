package com.example.demo.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.EraseMapper;

/**
 * データベース上の不要なデータを物理的に消すための処理を定時実行するクラス<br>
 * これに使われるSQLは自動生成されたMapperクラスたちとはほかに別に作る。
 * 
 * @see EraseMapper
 */
@Component
public class EraseScheduledTask {
	
	@Autowired
	EraseMapper eraseMapper;
	
	/**
	 * 定期実行する処理のエントリーポイント<br>
	 * やることが簡単に書かれている
	 */
	@Scheduled(cron="0 0 0 * * *")
	@Transactional(rollbackForClassName = "Exception")
	public void taskEntry() {
		eraseUser();
		eraseTalkRoom();
		eraseParent();
	}
	
	/**
	 * ユーザーに関する物理的に削除するべきものを削除する
	 */
	private void eraseUser() {
		//処理
		eraseMapper.eraseUnabledHaveUser();
		eraseMapper.eraseUnabledDesireHaveUser();
		eraseMapper.eraseUnabledUserInGroupByUnabledUser();
		eraseMapper.eraseUnabledGroupDesireByUnabledUser();
		eraseMapper.eraseUnabledTalkByUnabledUser();
		eraseMapper.eraseUnabledUser();
	}
	
	/**
	 * トークルームに関する物理的に削除するべきものを削除する
	 */
	private void eraseTalkRoom() {
		//前処理
		eraseMapper.setUnabledDialogueTalkRoom();
		eraseMapper.setUnabledGroupTalkRoom();
		
		//処理
		eraseMapper.eraseUnabledGroupDesireByUnabledGroup();
		eraseMapper.eraseUnabledTalkByGroup();
		eraseMapper.eraseUnabledTalkByDialogue();
		eraseMapper.eraseUnabledUserInGroup();
		eraseMapper.eraseUnabledGroup();
		eraseMapper.eraseUnabledDialogue();
	}
	
	/**
	 * どこにも参照されることもなくなった親を削除
	 */
	private void eraseParent() {
		eraseMapper.eraseUnabledParentUser();
		eraseMapper.eraseUnabledParentTalkRoom();
	}
}
