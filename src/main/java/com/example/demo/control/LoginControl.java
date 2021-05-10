package com.example.demo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.exception.LoginException;
import com.example.demo.form.Groups;
import com.example.demo.form.UserForm;
import com.example.demo.security.JWTProvider;

/**
 * ログイン用のアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL)
@RestController
public class LoginControl {
	@Autowired
	JWTProvider provider;
	
	/**
	 * apiの呼び出し: /user/login(POST)<br>
	 * ログイン・認証トークンを返す
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @return 認証トークン<br>
	 *	これをクライアント側が使うことによりログイン状態と識別することができる
	*/
	@PostMapping("user/login")
	public String login(@RequestBody @Validated(Groups.Login.class) UserForm form) throws LoginException {
		return provider.loginAndGetToken(form.getUserIdName(), form.getPassword());
	}
}
