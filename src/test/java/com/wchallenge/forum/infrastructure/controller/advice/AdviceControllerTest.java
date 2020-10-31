package com.wchallenge.forum.infrastructure.controller.advice;

import static org.assertj.core.api.Assertions.assertThat;

import com.wchallenge.forum.domain.exception.DataNotFoundException;
import com.wchallenge.forum.domain.exception.ForumNotificationCode;
import com.wchallenge.forum.infrastructure.controller.dto.ApiResponseForumDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class AdviceControllerTest {

	private final AdviceController adviceController = new AdviceController();

	@Test
	void handleAllExceptions() {

		// Arrange
		UnsupportedOperationException exception = new UnsupportedOperationException("error");

		// Act
		ResponseEntity<ApiResponseForumDto<Object>> responseForumDtoResponseEntity = adviceController
			.handleAllExceptions(exception);

		// Assert
		assertThat(responseForumDtoResponseEntity.getStatusCode())
			.isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(responseForumDtoResponseEntity.getBody().getNotification().getCode())
			.isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		assertThat(responseForumDtoResponseEntity.getBody().getNotification().getDescription())
			.isEqualTo(exception.getMessage());
	}

	@Test
	void handleNotFoundExceptions() {

		// Arrange
		DataNotFoundException exception = new DataNotFoundException(
			ForumNotificationCode.DATA_NOT_FOUND);

		// Act
		ResponseEntity<ApiResponseForumDto<Object>> responseForumDtoResponseEntity = adviceController
			.handleAllExceptions(exception);

		// Assert
		assertThat(responseForumDtoResponseEntity.getStatusCode())
			.isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(responseForumDtoResponseEntity.getBody().getNotification().getCode())
			.isEqualTo(HttpStatus.NOT_FOUND.toString());
		assertThat(responseForumDtoResponseEntity.getBody().getNotification().getDescription())
			.isEqualTo(exception.getMessage());
	}
}