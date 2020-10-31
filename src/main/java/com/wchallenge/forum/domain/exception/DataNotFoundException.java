package com.wchallenge.forum.domain.exception;

import com.wchallenge.forum.infrastructure.config.Messages;

public class DataNotFoundException extends ForumException {

	public DataNotFoundException(ForumNotificationCode notificationCode) {

		super(Messages.getMessage(notificationCode.getMessage()), notificationCode);
	}
}
