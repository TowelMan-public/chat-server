package com.example.demo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.entity.response.DesireUserInGroupResponce;
import com.example.demo.form.DesireGroupForm;
import com.example.demo.form.Groups;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.DesireGroupService;

/**
 * グループ加入してほしい申請に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/desire/group")
@RestController
public class DesireGroupControl {
	@Autowired
	DesireGroupService desireGroupService;
	
	@GetMapping("gets")
	public List<DesireUserInGroupResponce> getDesireGroup(@AuthenticationPrincipal UserDetailsImp user) {
		return desireGroupService.getDesireGroup(user);
	}
	
	@PostMapping("delete")
	public void deleteDesireGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Delete.class) DesireGroupForm form) {
		desireGroupService.deleteDesireGroup(user,form.getTalkRoomId());
	}
	
	@PostMapping("join")
	public void joinGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Join.class) DesireGroupForm form) {
		desireGroupService.joinGroup(user,form.getTalkRoomId());
	}
}
