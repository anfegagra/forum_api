package com.wchallenge.forum.infrastructure.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Messages {

	public enum MessageName {

		DATA_NOT_FOUND
	}

	private static final MessageSourceAccessor accessor;

	static {
		accessor = initAccessor();
	}

	private static MessageSourceAccessor initAccessor() {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");

		return new MessageSourceAccessor(messageSource);
	}

	public static String getMessage(Messages.MessageName messageName) {

		String messageFromProperties = messageName.name().toLowerCase().replace("_", ".");

		return accessor.getMessage(messageFromProperties);
	}
}
