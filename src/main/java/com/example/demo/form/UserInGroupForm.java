package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.configurer.RegexpMessage;
import com.example.demo.configurer.SizeConfig;

import lombok.Data;

@Data
public class UserInGroupForm {
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Delete.class,Groups.Gets.class,Groups.Insert.class,Groups.Exit.class})
	private Integer talkRoomId;
	@Size(max=SizeConfig.NAME_MAX_SIZE, message=RegexpMessage.NAME_MAX_SIZE, groups= {Groups.Delete.class,Groups.Insert.class})
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Delete.class,Groups.Insert.class})
	private String userIdName;
}
