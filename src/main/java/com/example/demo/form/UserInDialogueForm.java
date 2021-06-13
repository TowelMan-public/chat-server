package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.configurer.RegexpMessage;
import com.example.demo.configurer.SizeConfig;

import lombok.Data;

@Data
public class UserInDialogueForm {
	@Size(max=SizeConfig.NAME_MAX_SIZE, message=RegexpMessage.NAME_MAX_SIZE, groups= {Groups.Delete.class,Groups.Insert.class,Groups.Get.class})
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Delete.class,Groups.Insert.class,Groups.Get.class})
	private String userIdName;
}
