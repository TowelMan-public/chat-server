package com.example.demo.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.form.DesireUserForm;
import com.example.demo.form.Groups;
import com.example.demo.security.UserDetailsImp;

/**
 * 友達追加申請に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/desire/user")
@RestController
public class DesireUserControl {
	@GetMapping("gets")
	public void getDesireUser(@AuthenticationPrincipal UserDetailsImp user) {
		//TODO
	}
	
	@PostMapping("delete")
	public void deleteDesireUser(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Delete.class) DesireUserForm form) {
		//TODO
	}
	
	@PostMapping("join")
	public void joinUser(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Join.class) DesireUserForm form) {
		//TODO
	}
}
