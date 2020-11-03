package com.wchallenge.forum.domain.exception;

import com.wchallenge.forum.infrastructure.config.Messages.MessageName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ForumNotificationCode {

	DATA_NOT_FOUND(MessageName.DATA_NOT_FOUND, "FRM_DNF"),
	USERNAME_NOT_FOUND(MessageName.USERNAME_NOT_FOUND, "FRM_UNF"),
	ACCESS_DENIED(MessageName.ACCESS_DENIED, "FRM_AD"),
	BAD_CREDENTIALS(MessageName.BAD_CREDENTIALS, "FRM_BC");

	private final MessageName message;
	private final String code;

}
