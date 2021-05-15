package com.example.demo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotHaveUserException;
import com.example.demo.form.DialogueForm;
import com.example.demo.form.Groups;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.DialogueService;

/**
 * 友達トークに関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/diarogue")
@RestController
public class DialogueControl {
	
	@Autowired
	DialogueService dialogueService;
	
	/**
	 * APIの呼び出し: /dialogue/gets/talks(GET)<br>
	 * 友達トークの取得<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @return トークリスト
	 * @throws NotFoundException
	 * @throws NotHaveUserException
	 */
	@GetMapping("gets/talks")
	public List<TalkResponse> getDiarogueTalks(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Gets.class) DialogueForm form)
			throws NotFoundException, NotHaveUserException {
		return dialogueService.getDiarogueTalks(user,form.getUserIdName(),form.getStartIndex(),form.getMaxSize());
	}
}
