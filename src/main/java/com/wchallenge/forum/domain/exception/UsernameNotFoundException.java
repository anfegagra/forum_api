package com.wchallenge.forum.domain.exception;

import com.wchallenge.forum.infrastructure.config.Messages;

public class UsernameNotFoundException extends ForumException {

	public UsernameNotFoundException(ForumNotificationCode notificationCode) {

		super(Messages.getMessage(notificationCode.getMessage()), notificationCode);
	}
}
