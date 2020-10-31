package com.wchallenge.forum.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponseForumDto<T> {

	private T data;
	private NotificationDto notification;
}
