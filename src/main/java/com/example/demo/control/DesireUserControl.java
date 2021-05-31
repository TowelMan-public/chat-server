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
import com.example.demo.entity.response.DesireHaveUserResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.form.DesireUserForm;
import com.example.demo.form.Groups;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.DesireUserService;

/**
 * 友達追加申請に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/desire/user")
@RestController
public class DesireUserControl {
	@Autowired
	DesireUserService desireUserService;
	
	/**
	 * APIの呼び出し: /desire/user/gets(GET)<br>
	 * 友達追加申請一覧取得
	 * @param user アクセスしたユーザーの情報
	 * @return 友達追加申請一覧
	 */
	@GetMapping("gets")
	public List<DesireHaveUserResponse> getDesireUserList(@AuthenticationPrincipal UserDetailsImp user) {
		return desireUserService.getDesireUserList(user);
	}
	
	/**
	 * APIの呼び出し: /desire/user/get(GET)<br>
	 * 友達追加申請取得
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @return 友達追加申請
	 * @throws NotFoundException 
	 */
	@GetMapping("get")
	public DesireHaveUserResponse getDesireUser(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Get.class) DesireUserForm form)
			throws NotFoundException {
		return desireUserService.getDesireUser(user, form.getUserIdName());
	}
	
	/**
	 * APIの呼び出し: /desire/user/delete(POST)<br>
	 * 友達申請削除
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotFoundException
	 */
	@PostMapping("delete")
	public void deleteDesireUser(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Delete.class) DesireUserForm form)
			throws NotFoundException {
		desireUserService.deleteDesireUser(user,form.getUserIdName());
	}
	
	/**
	 * APIの呼び出し: /desire/user/join(POST)<br>
	 * 友達申請を受ける
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotFoundException
	 */
	@PostMapping("join")
	public void joinUser(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Join.class) DesireUserForm form)
			throws NotFoundException {
		desireUserService.joinUser(user,form.getUserIdName());
	}
}
