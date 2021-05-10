package com.example.demo.form;

import javax.validation.constraints.NotBlank;

import com.example.demo.configurer.RegexpMessage;

import lombok.Data;

@Data
public class UserForm {
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Insert.class,Groups.UpdateName.class})
	private String userName;
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Insert.class,Groups.Get.class,Groups.UpdateIdName.class,
			Groups.Login.class})
	private String userIdName;
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Insert.class,Groups.UpdatePassword.class,Groups.Login.class})
	private String password;
}
