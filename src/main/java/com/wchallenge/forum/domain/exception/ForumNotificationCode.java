package com.wchallenge.forum.domain.exception;

import com.wchallenge.forum.infrastructure.config.Messages.MessageName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ForumNotificationCode {

	DATA_NOT_FOUND(MessageName.DATA_NOT_FOUND, "FRM_DNF");

	private final MessageName message;
	private final String code;
}
