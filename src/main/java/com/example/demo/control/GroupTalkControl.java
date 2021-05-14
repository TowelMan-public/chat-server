package com.example.demo.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.form.Groups;
import com.example.demo.form.TalkForm;
import com.example.demo.security.UserDetailsImp;

/**
 * グループトーク単体に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/group/talk")
@RestController
public class GroupTalkControl {
	@PostMapping("insert")
	public void insertTalk(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Insert.class) TalkForm form) {
		//TODO
	}
	
	@GetMapping("get")
	public void getTalk(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Get.class) TalkForm form) {
		//TODO
	}
	
	@PostMapping("update")
	public void updateTalk(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Update.class) TalkForm form) {
		//TODO
	}
	
	@PostMapping("delete")
	public void deleteTalk(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Delete.class) TalkForm form) {
		//TODO
	}
}
