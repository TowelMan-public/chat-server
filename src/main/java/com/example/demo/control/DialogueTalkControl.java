package com.example.demo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.BadRequestFormException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotHaveUserException;
import com.example.demo.form.DiarogueTalkForm;
import com.example.demo.form.Groups;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.DialogueTalkService;

/**
 * 友達トーク単体に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/dialogue/talk")
@RestController
public class DialogueTalkControl {
	@Autowired
	DialogueTalkService dialogueTalkService;
	
	/**
	 * APIの呼び出し: /dialogue/talk/insert(POST)<br>
	 * 友達トーク作成<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	@PostMapping("insert")
	public void insertTalk(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Insert.class) DiarogueTalkForm form) 
			throws NotFoundException, NotHaveUserException {
		dialogueTalkService.insertTalk(user, form.getHaveUserIdName(), form.getTalkContentText());
	}
	
	/**
	 * APIの呼び出し: /dialogue/talk/get(GET)<br>
	 * 友達トーク単体取得<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @return
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	@GetMapping("get")
	public TalkResponse getTalk(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Get.class) DiarogueTalkForm form) 
			throws NotFoundException, NotHaveUserException {
		return dialogueTalkService.getTalk(user, form.getHaveUserIdName(), form.getTalkIndex());
	}
	
	/**
	 * APIの呼び出し: /dialogue/talk/update(POST)<br>
	 * 友達トーク変更<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 * @throws BadRequestFormException
	 */
	@PostMapping("update")
	public void updateTalk(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Update.class) DiarogueTalkForm form) 
			throws NotFoundException, NotHaveUserException, BadRequestFormException {
		dialogueTalkService.updateTalk(user, form.getHaveUserIdName(), form.getTalkIndex(), form.getTalkContentText());
	}
	
	/**
	 * APIの呼び出し: /dialogue/talk/delete(POST)<br>
	 * 友達トーク削除<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 * @throws BadRequestFormException
	 */
	@PostMapping("delete")
	public void deleteTalk(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Delete.class) DiarogueTalkForm form) 
			throws NotFoundException, NotHaveUserException, BadRequestFormException {
		dialogueTalkService.deleteTalk(user, form.getHaveUserIdName(), form.getTalkIndex());
	}
}