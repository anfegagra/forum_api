package com.wchallenge.forum.domain.exception;

import com.wchallenge.forum.infrastructure.config.Messages;

public class BadCredentialsException extends ForumException {

	public BadCredentialsException(ForumNotificationCode notificationCode) {

		super(Messages.getMessage(notificationCode.getMessage()), notificationCode);
	}
}
