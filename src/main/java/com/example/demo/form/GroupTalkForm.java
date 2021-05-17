package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.configurer.RegexpMessage;

import lombok.Data;

@Data
public class GroupTalkForm {
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Get.class,Groups.Insert.class,Groups.Update.class,Groups.Delete.class})
	private Integer talkRoomId;
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Insert.class,Groups.Update.class})
	private String talkContentText;
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Get.class,Groups.Update.class,Groups.Delete.class})
	private Integer talkIndex;
}
