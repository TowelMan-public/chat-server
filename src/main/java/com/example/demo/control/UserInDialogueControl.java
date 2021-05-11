package com.example.demo.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.form.Groups;
import com.example.demo.form.UserInDialogueForm;
import com.example.demo.security.UserDetailsImp;

/**
 * ユーザーが持っている友達に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/diarogue/user")
@RestController
public class UserInDialogueControl {
	@GetMapping("gets")
	public void getUsersInDiarogue(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Gets.class) UserInDialogueForm form) {
		//TODO
	}
	
	@PostMapping("insert")
	public void insertUserInDiarogue(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Insert.class) UserInDialogueForm form) {
		//TODO
	}
	
	@PostMapping("delete")
	public void deleteUserInDiarogue(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Delete.class) UserInDialogueForm form) {
		//TODO
	}
}
