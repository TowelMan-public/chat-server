package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.configurer.RegexpMessage;
import com.example.demo.configurer.SizeConfig;

import lombok.Data;

@Data
public class DialogueForm {
	@Size(max=SizeConfig.NAME_MAX_SIZE, message=RegexpMessage.NAME_MAX_SIZE, groups= {Groups.Gets.class})
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Gets.class})
	private String userIdName;
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Gets.class})
	private Integer maxSize;
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Gets.class})
	private Integer startIndex;
}
