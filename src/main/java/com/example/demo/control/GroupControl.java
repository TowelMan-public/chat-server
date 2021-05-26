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
import com.example.demo.entity.GroupTalkRoomEntity;
import com.example.demo.entity.response.GroupTalkRoomResponse;
import com.example.demo.entity.response.TalkResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotJoinGroupException;
import com.example.demo.form.GroupForm;
import com.example.demo.form.Groups;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.GroupService;

/**
 * グループトークに関するAPIのアクセスポイントクラス
 */
@RequestMapping(UrlConfing.ROOT_URL + "/group")
@RestController
public class GroupControl {
	
	@Autowired
	GroupService groupService;
	
	/**
	 * APIの呼び出し: /group/get(GET)<br>
	 * グループ名等を取得<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @return グループ
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 */
	@GetMapping("get")
	public GroupTalkRoomEntity getGroup(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Get.class) GroupForm form)
			throws NotJoinGroupException, NotFoundException {
		return groupService.getGroup(user, form.getTalkRoomId());
	}
	
	/**
	 * APIの呼び出し: /group/gets(GET)<br>
	 * グループリストの取得<br>
	 * @param user アクセスしたユーザーの情報
	 * @return　グループリスト
	 */
	@GetMapping("gets")
	public List<GroupTalkRoomResponse> getGroups(@AuthenticationPrincipal UserDetailsImp user) {
		return groupService.getGroups(user);
	}
	
	/**
	 * APIの呼び出し: /group/update/name(POST)<br>
	 * グループ名の更新<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 */
	@PostMapping("update/name")
	public void updateGroupName(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.UpdateName.class) GroupForm form) 
			throws NotJoinGroupException, NotFoundException {
		groupService.updateGroupName(user, form.getTalkRoomId(), form.getGroupName());
	}
	
	/**
	 * APIの呼び出し: /group/gets/talks(GET)<br>
	 * グループトークの取得<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @return グループトーク
	 * @throws NotFoundException
	 * @throws NotJoinGroupException
	 */
	@GetMapping("gets/talks")
	public List<TalkResponse> getGroupTalks(@AuthenticationPrincipal UserDetailsImp user, @Validated(Groups.Gets.class) GroupForm form) 
			throws NotFoundException, NotJoinGroupException {
		return groupService.getGroupTalks(user, form.getTalkRoomId(), form.getStartIndex(), form.getMaxSize());
	}
	
	/**
	 * APIの呼び出し: /group/delete(POST)<br>
	 * グループ削除<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 * @throws NotJoinGroupException
	 * @throws NotFoundException
	 */
	@PostMapping("delete")
	public void deleteGroup(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Delete.class) GroupForm form) 
			throws NotJoinGroupException, NotFoundException {
		groupService.deleteGroup(user, form.getTalkRoomId());
	}
	
	/**
	 * APIの呼び出し: /group/insert(POST)<br>
	 * グループ作成<br>
	 * @param user アクセスしたユーザーの情報
	 * @param form リクエストのパラメター<br>
	 * 	ここで入力ﾁｪｯｸも行う
	 */
	@PostMapping("insert")
	public void insertGroup(@AuthenticationPrincipal UserDetailsImp user, @RequestBody @Validated(Groups.Insert.class) GroupForm form) {
		groupService.insertGroup(user,form.getGroupName());
	}
}
