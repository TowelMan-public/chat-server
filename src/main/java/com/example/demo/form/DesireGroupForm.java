package com.example.demo.form;

import javax.validation.constraints.NotNull;

import com.example.demo.configurer.RegexpMessage;

import lombok.Data;

@Data
public class DesireGroupForm {
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Delete.class,Groups.Join.class})
	private Integer talkRoomId;
}
