package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.configurer.RegexpMessage;
import com.example.demo.configurer.SizeConfig;

import lombok.Data;

@Data
public class GroupTalkForm {
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Get.class,Groups.Insert.class,Groups.Update.class,Groups.Delete.class})
	private Integer talkRoomId;
	@Size(max=SizeConfig.CONTENT_TEXT_MAX_SIZE, message=RegexpMessage.CONTENT_TEXT_MAX_SIZE, groups= {Groups.Insert.class,Groups.Update.class})
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Insert.class,Groups.Update.class})
	private String talkContentText;
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Get.class,Groups.Update.class,Groups.Delete.class})
	private Integer talkIndex;
}
