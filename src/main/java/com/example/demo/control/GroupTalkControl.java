package com.example.demo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.BadRequestFormException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotHaveUserException;
import com.example.demo.exception.NotJoinGroupException;
import com.example.demo.form.GroupTalkForm;
import com.example.demo.form.Groups;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.GroupTalkService;

/**
 * グループトーク単体に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/group/talk")
@RestController
public class GroupTalkControl {
	@Autowired
	GroupTalkService groupTalkService;
	
	/**
	 * APIの呼び出し: /group/talk/insert(POST)<br>
	 * グループトーク作成<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	@PostMapping("insert")
	public void insertTalk(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Insert.class) GroupTalkForm form)
			throws NotJoinGroupException, NotFoundException {
		groupTalkService.insertTalk(user, form.getTalkRoomId(), form.getTalkContentText());
	}
	
	/**
	 * APIの呼び出し: /group/talk/get(GET)<br>
	 * グループトーク単体取得<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @return
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	@GetMapping("get")
	public TalkResponse getTalk(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Get.class) GroupTalkForm form)
			throws NotJoinGroupException, NotFoundException {
		return groupTalkService.getTalk(user, form.getTalkRoomId(), form.getTalkIndex());
	}
	
	/**
	 * APIの呼び出し: /group/talk/update(POST)<br>
	 * グループトーク変更<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 * @throws BadRequestFormException
	 */
	@PostMapping("update")
	public void updateTalk(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Update.class) GroupTalkForm form) 
			throws NotJoinGroupException, NotFoundException, BadRequestFormException {
		groupTalkService.updateTalk(user, form.getTalkRoomId(), form.getTalkIndex(), form.getTalkContentText());
	}
	
	/**
	 * APIの呼び出し: /group/talk/delete(POST)<br>
	 * グループトーク削除<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 * @throws BadRequestFormException
	 */
	@PostMapping("delete")
	public void deleteTalk(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Delete.class) GroupTalkForm form) 
			throws NotJoinGroupException, NotFoundException, BadRequestFormException {
		groupTalkService.deleteTalk(user, form.getTalkRoomId(), form.getTalkIndex());
	}
}
