package com.example.demo.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.form.DesireGroupForm;
import com.example.demo.form.Groups;
import com.example.demo.security.UserDetailsImp;

/**
 * グループ加入してほしい申請に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/desire/group")
@RestController
public class DesireGroupControl {
	@GetMapping("gets")
	public void getDesireGroup(@AuthenticationPrincipal UserDetailsImp user) {
		//TODO
	}
	
	@PostMapping("delete")
	public void deleteDesireGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Delete.class) DesireGroupForm form) {
		//TODO
	}
	
	@PostMapping("join")
	public void joinGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Join.class) DesireGroupForm form) {
		//TODO
	}
}
