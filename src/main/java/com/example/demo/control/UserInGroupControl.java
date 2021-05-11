package com.example.demo.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.form.Groups;
import com.example.demo.form.UserInGroupForm;
import com.example.demo.security.UserDetailsImp;

/**
 * グループ加入者に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/group/user")
@RestController
public class UserInGroupControl {
	@PostMapping("insert")
	public void insertUserInGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Insert.class) UserInGroupForm form) {
		//TODO
	}
	
	@GetMapping("gets")
	public void getUsersInGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Gets.class) UserInGroupForm form) {
		//TODO
	}
	
	@PostMapping("delete")
	public void deleteUserInGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Delete.class) UserInGroupForm form) {
		//TODO
	}
	
	@PostMapping("exit")
	public void exitGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Exit.class) UserInGroupForm form) {
		//TODO
	}
}
