package com.example.demo.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.form.Groups;
import com.example.demo.form.UserForm;
import com.example.demo.security.UserDetailsImp;

/**
 * ログインを省くユーザーに関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/user")
@RestController
public class UserControl {
	
	@PostMapping("insert")
	public void insertUser(@AuthenticationPrincipal UserDetailsImp user,@RequestBody @Validated(Groups.Insert.class) UserForm form) {
		//TODO
	}
	
	@GetMapping("get")
	public void getUser(@AuthenticationPrincipal UserDetailsImp user,@Validated(Groups.Get.class) UserForm form) {
		//TODO
	}
	
	@GetMapping("gets/groups")
	public void getGroups(@AuthenticationPrincipal UserDetailsImp user) {
		//TODO
	}
	
	@PostMapping("update/id-name")
	public void updateUserIdName(@AuthenticationPrincipal UserDetailsImp user,@RequestBody @Validated(Groups.UpdateIdName.class) UserForm form) {
		//TODO
	}
	
	@PostMapping("update/name")
	public void updateUserName(@AuthenticationPrincipal UserDetailsImp user,@RequestBody @Validated(Groups.UpdateName.class) UserForm form) {
		//TODO
	}
	
	@PostMapping("update/password")
	public void updatePassword(@AuthenticationPrincipal UserDetailsImp user,@RequestBody @Validated(Groups.UpdatePassword.class) UserForm form) {
		//TODO
	}
	
	@PostMapping("delete")
	public void deleteUser(@AuthenticationPrincipal UserDetailsImp user,@RequestBody @Validated(Groups.Delete.class) UserForm form) {
		//TODO
	}
}
