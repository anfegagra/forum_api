package com.wchallenge.forum.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiResponseForumDto<T> {

	private T data;
	private NotificationDto notification;
}
