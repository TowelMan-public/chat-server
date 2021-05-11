package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.configurer.RegexpMessage;

import lombok.Data;

@Data
public class DialogueForm {
	@NotBlank(message=RegexpMessage.EMPTY,groups= {Groups.Gets.class})
	private String userIdName;
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Gets.class})
	private Integer maxSize;
	@NotNull(message=RegexpMessage.EMPTY,groups= {Groups.Gets.class})
	private Integer startIndex;
}
