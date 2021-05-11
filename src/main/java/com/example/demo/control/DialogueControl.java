package com.example.demo.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.form.DialogueForm;
import com.example.demo.form.Groups;
import com.example.demo.security.UserDetailsImp;

/**
 * 友達トークに関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/diarogue")
@RestController
public class DialogueControl {
	@GetMapping("gets/talks")
	public void getDiarogueTalks(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Gets.class) DialogueForm form) {
		//TODO
	}
}
