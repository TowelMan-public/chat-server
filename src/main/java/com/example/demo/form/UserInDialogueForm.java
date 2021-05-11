package com.example.demo.form;

import javax.validation.constraints.NotBlank;

import com.example.demo.configurer.RegexpMessage;

import lombok.Data;

@Data
public class UserInDialogueForm {
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Delete.class,Groups.Insert.class})
	private String userIdName;
}
