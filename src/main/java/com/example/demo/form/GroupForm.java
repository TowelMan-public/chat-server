package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.configurer.RegexpMessage;
import com.example.demo.form.Groups.UpdateName;

import lombok.Data;

@Data
public class GroupForm {
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Gets.class,Groups.Get.class,UpdateName.class,Groups.Delete.class})
	private Integer talkRoomId;
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Insert.class,Groups.UpdateName.class})
	private String groupName;
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Gets.class})
	private Integer maxSize;
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Gets.class})
	private Integer startIndex;
}
