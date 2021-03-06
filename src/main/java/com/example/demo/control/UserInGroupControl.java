package com.example.demo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfing;
import com.example.demo.entity.response.UserInGroupResponse;
import com.example.demo.exception.AlreadyInsertedGroupDesireException;
import com.example.demo.exception.AlreadyInsertedGroupException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotJoinGroupException;
import com.example.demo.form.Groups;
import com.example.demo.form.UserInGroupForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserInGroupService;

/**
 * グループ加入者に関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/group/user")
@RestController
public class UserInGroupControl {
	
	@Autowired
	UserInGroupService userInGroupService;
	
	/**
	 * APIの呼び出し: /group/user/insert(POST)<br>
	 * グループにユーザーを加入させる
	 * @param user アクセスしたユーザーの情報
	 * @return グループ加入申請一覧
	 * @throws NotFoundException
	 * @throws NotJoinGroupException
	 * @throws AlreadyInsertedGroupDesireException 
	 * @throws AlreadyInsertedGroupException 
	 */
	@PostMapping("insert")
	public void insertUserInGroup(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Insert.class) UserInGroupForm form)
			throws NotFoundException, NotJoinGroupException, AlreadyInsertedGroupDesireException, AlreadyInsertedGroupException {
		userInGroupService.insertUserInGroup(user,form.getTalkRoomId(),form.getUserIdName());
	}
	
	/**
	 * APIの呼び出し: /group/user/gets(GET)<br>
	 * グループ加入者取得
	 * @param user アクセスしたユーザーの情報
	 * @return グループ加入申請一覧
	 * @throws NotFoundException 
	 */
	@GetMapping("gets")
	public List<UserInGroupResponse> getUsersInGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Gets.class) UserInGroupForm form)
			throws NotJoinGroupException, NotFoundException {
		return userInGroupService.getUsersInGroup(user,form.getTalkRoomId());
	}
	
	/**
	 * APIの呼び出し: /group/user/delete(POST)<br>
	 * グループ加入者削除
	 * @param user アクセスしたユーザーの情報
	 * @return グループ加入申請一覧
	 * @throws NotFoundException
	 * @throws NotJoinGroupException
	 */
	@PostMapping("delete")
	public void deleteUserInGroup(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Delete.class) UserInGroupForm form)
			throws NotFoundException, NotJoinGroupException {
		userInGroupService.deleteUserInGroup(user,form.getTalkRoomId(),form.getUserIdName());
	}
	
	/**
	 * APIの呼び出し: /group/user/exit(POST)<br>
	 * グループから抜ける
	 * @param user アクセスしたユーザーの情報
	 * @return グループ加入申請一覧
	 * @throws NotJoinGroupException
	 * @throws NotFoundException 
	 */
	@PostMapping("exit")
	public void exitGroup(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Exit.class) UserInGroupForm form)
			throws NotJoinGroupException, NotFoundException {
		userInGroupService.exitGroup(user,form.getTalkRoomId());
	}
}
