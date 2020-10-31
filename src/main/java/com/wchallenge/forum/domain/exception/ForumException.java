package com.wchallenge.forum.domain.exception;

import lombok.Getter;

public class ForumException extends RuntimeException {

	@Getter
	private final ForumNotificationCode notificationCode;

	public ForumException(String message, ForumNotificationCode notificationCode) {

		super(message);
		this.notificationCode = notificationCode;
	}

}
