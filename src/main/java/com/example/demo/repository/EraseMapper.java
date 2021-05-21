package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.batch.EraseScheduledTask;

/**
 * EraseScheduledTaskクラスのためのマッパークラス
 * @see EraseScheduledTask
 */
@Mapper
public interface EraseMapper {

	/**
	 * 削除するべき友達リストの物理的削除
	 */
	public void eraseUnabledHaveUser();

	/**
	 * 削除するべき友達申請リストの物理的削除
	 */
	public void eraseUnabledDesireHaveUser();

	/**
	 * 削除するべきグループ加入者リストの物理的削除
	 */
	public void eraseUnabledUserInGroupByUnabledUser();
	
	/**
	 * 削除するべきグループに加入してほしい申請リストの物理的削除
	 */
	public void eraseUnabledGroupDesireByUnabledUser();

	/**
	 * 削除するべきトークの物理的削除　ユーザーから
	 */
	public void eraseUnabledTalkByUnabledUser();
	
	/**
	 * 削除するべきユーザーの物理的削除
	 */
	public void eraseUnabledUser();

	/**
	 * 削除するべきユーザートークルームを有効でないものにする（有効でない=削除するべき）
	 */
	public void setUnabledDialogueTalkRoom();

	/**
	 * 削除するべきグループトークルームを有効でないものにする（有効でない=削除するべき）
	 */
	public void setUnabledGroupTalkRoom();

	/**
	 * 削除するべきグループに加入してほしい申請リストの物理的削除
	 */
	public void eraseUnabledGroupDesireByUnabledGroup();

	/**
	 * 削除するべきトークの物理的削除　グループトークルームから
	 */
	public void eraseUnabledTalkByGroup();
	
	/**
	 * 削除するべきトークの物理的削除 友達トークルームから
	 */
	public void eraseUnabledTalkByDialogue();

	/**
	 * 削除するべきグループ加入者リストの物理的削除
	 */
	public void eraseUnabledUserInGroup();

	/**
	 * 削除するべきグループトークルームの物理的削除
	 */
	public void eraseUnabledGroup();

	/**
	 * 削除するべき友達トークルームの物理的削除
	 */
	public void eraseUnabledDialogue();

	/**
	 * 削除するべきユーザーの親レコードの物理的削除
	 */
	public void eraseUnabledParentUser();

	/*
	 * 削除するべきトークルームの親レコードの物理的削除
	 */
	public void eraseUnabledParentTalkRoom();
}
