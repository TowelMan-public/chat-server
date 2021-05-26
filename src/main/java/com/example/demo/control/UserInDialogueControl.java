package com.example.demo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.entity.response.HaveUserResponse;
import com.example.demo.exception.AlreadyHaveUserException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotHaveUserException;
import com.example.demo.form.Groups;
import com.example.demo.form.UserInDialogueForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserInDialogueService;

/**
 * ユーザーが持っている友達に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/diarogue/user")
@RestController
public class UserInDialogueControl {
	
	@Autowired
	UserInDialogueService userInDialogueService;
	
	/**
	 * APIの呼び出し: /dialogue/user/gets(GET)<br>
	 * 友達一覧取得
	 * @param user アクセスしたユーザーの情報
	 * @return 友達一覧
	 */
	@GetMapping("gets")
	public List<HaveUserResponse> getUserInDiarogueList(@AuthenticationPrincipal UserDetailsImp user) {
		return userInDialogueService.getUserInDiarogueList(user);
	}
	
	/**
	 * APIの呼び出し: /dialogue/user/insert(POST)<br>
	 * 友達追加
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotFoundException
	 * @throws AlreadyHaveUserException
	 */
	@PostMapping("insert")
	public void insertUserInDiarogue(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Insert.class) UserInDialogueForm form)
			throws NotFoundException, AlreadyHaveUserException {
		userInDialogueService.insertUserInDiarogue(user,form.getUserIdName());
	}
	
	/**
	 * APIの呼び出し: /dialogue/user/delete(POST)<br>
	 * 友達削除
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	@PostMapping("delete")
	public void deleteUserInDiarogue(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Delete.class) UserInDialogueForm form) 
			throws NotFoundException, NotHaveUserException {
		userInDialogueService.deleteUserInDiarogue(user,form.getUserIdName());
	}
}
