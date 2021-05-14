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
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.form.Groups;
import com.example.demo.form.UserForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserService;

/**
 * ログインを省くユーザーに関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/user")
@RestController
public class UserControl {
	
	@Autowired
	UserService userService;
	
	/**
	 * APIの呼び出し: /user/insert(POST)<br>
	 * ユーザー作成<br>
	 * 認証無でアクセス
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	*/
	@PostMapping("insert")
	public void insertUser(@RequestBody @Validated(Groups.Insert.class) UserForm form) {
		userService.insertUser(form);
	}
	
	/**
	 * APIの呼び出し: /user/get(GET)<br>
	 * ユーザー情報取得
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @return 
	 * @throws NotFoundException
	 * @throws UnEnableException
	 */
	@GetMapping("get")
	public UserEntity getUser(@Validated(Groups.Get.class) UserForm form) throws NotFoundException {
		return userService.getUser(form.getUserIdName());
	}
	
	/**
	 * APIの呼び出し: /user/update/id-name(POST)<br>
	 * ユーザーID（名前）の変更
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 */
	@PostMapping("update/id-name")
	public void updateUserIdName(@AuthenticationPrincipal UserDetailsImp user,@RequestBody @Validated(Groups.UpdateIdName.class) UserForm form) {
		userService.updateUserIdName(user,form.getUserIdName());
	}
	
	/**
	 * APIの呼び出し: /user/update/name(POST)<br>
	 * ユーザー名（ニックネーム）の変更
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 */
	@PostMapping("update/name")
	public void updateUserName(@AuthenticationPrincipal UserDetailsImp user,@RequestBody @Validated(Groups.UpdateName.class) UserForm form) {
		userService.updateUserName(user,form.getUserName());
	}
	
	/**
	 * APIの呼び出し: /user/update/password(POST)<br>
	 * パスワードの変更
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 */
	@PostMapping("update/password")
	public void updatePassword(@AuthenticationPrincipal UserDetailsImp user,@RequestBody @Validated(Groups.UpdatePassword.class) UserForm form) {
		userService.updatePassword(user,form.getPassword());
	}
	
	/**
	 * APIの呼び出し: /user/delete(POST)<br>
	 * ユーザーの削除（退会）
	 * @param user アクセスしたユーザーの情報
	 */
	@PostMapping("delete")
	public void deleteUser(@AuthenticationPrincipal UserDetailsImp user) {
		userService.deleteUser(user);
	}
}
