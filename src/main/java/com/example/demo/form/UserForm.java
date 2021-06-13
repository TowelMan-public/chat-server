package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.configurer.RegexpMessage;
import com.example.demo.configurer.SizeConfig;

import lombok.Data;

@Data
public class UserForm {
	@Size(max=SizeConfig.NAME_MAX_SIZE, message=RegexpMessage.NAME_MAX_SIZE, groups= {Groups.Insert.class,Groups.UpdateName.class})
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Insert.class,Groups.UpdateName.class})
	private String userName;
	@Size(max=SizeConfig.NAME_MAX_SIZE, message=RegexpMessage.NAME_MAX_SIZE,
			groups= {Groups.Insert.class,Groups.Get.class,Groups.UpdateIdName.class, Groups.Login.class})
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Insert.class,Groups.Get.class,Groups.UpdateIdName.class,
			Groups.Login.class})
	private String userIdName;
	@Size(max=SizeConfig.NAME_MAX_SIZE, message=RegexpMessage.NAME_MAX_SIZE, 
			groups= {Groups.Insert.class,Groups.UpdatePassword.class,Groups.Login.class})
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Insert.class,Groups.UpdatePassword.class,Groups.Login.class})
	private String password;
}
