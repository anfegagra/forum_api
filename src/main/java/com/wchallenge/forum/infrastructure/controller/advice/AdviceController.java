package com.wchallenge.forum.infrastructure.controller.advice;

import com.wchallenge.forum.domain.exception.BadCredentialsException;
import com.wchallenge.forum.domain.exception.DataNotFoundException;
import com.wchallenge.forum.domain.exception.ForumException;
import com.wchallenge.forum.domain.exception.ForumNotificationCode;
import com.wchallenge.forum.domain.exception.UsernameNotFoundException;
import com.wchallenge.forum.infrastructure.config.Messages;
import com.wchallenge.forum.infrastructure.controller.dto.ApiResponseForumDto;
import com.wchallenge.forum.infrastructure.controller.dto.NotificationDto;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

@Slf4j
@ControllerAdvice
public class AdviceController {

	private static final List<ErrorDescriptor> ERROR_CATALOG = new ArrayList<>();

	static {
		ERROR_CATALOG.add(new ErrorDescriptor(DataNotFoundException.class,
			HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString()));
		ERROR_CATALOG.add(new ErrorDescriptor(UsernameNotFoundException.class,
			HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString()));
		ERROR_CATALOG.add(new ErrorDescriptor(BadCredentialsException.class,
			HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.toString()));
	}

	@ExceptionHandler({
		Exception.class
	})
	public final ResponseEntity<ApiResponseForumDto<Object>> handleAllExceptions(
		Exception exception) {

		log.error(exception.getMessage(), exception);

		ErrorDescriptor errorDescriptor = findDescriptorByException(exception);

		return ResponseEntity.status(findDescriptorByException(exception).httpStatus)
			.body(ApiResponseForumDto.builder().data(null)
				.notification(
					buildNotification(errorDescriptor.getResponseCode(), exception.getMessage()))
				.build());
	}

	@ExceptionHandler({
		DataNotFoundException.class,
		UsernameNotFoundException.class,
		BadCredentialsException.class
	})
	public final ResponseEntity<ApiResponseForumDto<Object>> handleCustomExceptions(
		ForumException exception) {

		log.error(exception.getMessage(), exception);

		return ResponseEntity.status(findDescriptorByException(exception).httpStatus)
			.body(ApiResponseForumDto.builder().data(null)
				.notification(buildNotification(exception.getNotificationCode().getCode(),
					exception.getMessage())).build());
	}

	@ExceptionHandler({
		AccessDeniedException.class
	})
	public final ResponseEntity<ApiResponseForumDto<Object>> handleAccessDeniedException(
		AccessDeniedException exception) {

		log.error(exception.getMessage(), exception);

		return ResponseEntity.status(HttpStatus.FORBIDDEN)
			.body(ApiResponseForumDto.builder().data(null)
				.notification(buildNotification(ForumNotificationCode.ACCESS_DENIED.getCode(),
					Messages.getMessage(ForumNotificationCode.ACCESS_DENIED.getMessage())))
				.build());
	}

	private ErrorDescriptor findDescriptorByException(Exception ex) {

		return ERROR_CATALOG.stream()
			.filter(errorDescriptor -> errorDescriptor.getExceptionType().equals(ex.getClass()))
			.findFirst()
			.orElse(new ErrorDescriptor(InternalServerError.class, HttpStatus.INTERNAL_SERVER_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.toString()));
	}

	private NotificationDto buildNotification(String code, String message) {

		return NotificationDto.builder().code(code).description(message).build();
	}

	@Getter
	private static class ErrorDescriptor {

		Class<? extends Throwable> exceptionType;
		HttpStatus httpStatus;
		String responseCode;

		private ErrorDescriptor(Class<? extends Throwable> exceptionType,
			HttpStatus httpStatus, String responseCode) {

			this.exceptionType = exceptionType;
			this.httpStatus = httpStatus;
			this.responseCode = responseCode;
		}

	}
}
