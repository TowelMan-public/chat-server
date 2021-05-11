package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.configurer.RegexpMessage;

import lombok.Data;

@Data
public class UserInGroupForm {
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Delete.class,Groups.Gets.class,Groups.Insert.class,Groups.Exit.class})
	private Integer talkRoomId;
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Delete.class,Groups.Insert.class})
	private String userIdName;
}
