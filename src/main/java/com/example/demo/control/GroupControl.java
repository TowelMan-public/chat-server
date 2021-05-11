package com.example.demo.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.form.GroupForm;
import com.example.demo.form.Groups;
import com.example.demo.security.UserDetailsImp;

/**
 * グループトークに関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/group")
@RestController
public class GroupControl {
	@GetMapping("get")
	public void getGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Get.class) GroupForm form) {
		//TODO
	}
	
	@PostMapping("update/name")
	public void updateGroupName(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.UpdateName.class) GroupForm form) {
		//TODO
	}
	
	@GetMapping("gets/talks")
	public void getGroupTalks(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Gets.class) GroupForm form) {
		//TODO
	}
	
	@PostMapping("delete")
	public void deleteGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Delete.class) GroupForm form) {
		//TODO
	}
	
	@PostMapping("insert")
	public void insertGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Insert.class) GroupForm form) {
		//TODO
	}
}
