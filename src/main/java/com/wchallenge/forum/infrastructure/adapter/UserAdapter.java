package com.wchallenge.forum.infrastructure.adapter;

import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.domain.port.UserPort;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.delegate.JsonPlaceholderDelegate;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.mapper.UserJsonPlaceholderMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserAdapter implements UserPort {

	private final JsonPlaceholderDelegate jsonPlaceholderDelegate;
	private final UserJsonPlaceholderMapper userJsonPlaceholderMapper;

	public UserAdapter(
		JsonPlaceholderDelegate jsonPlaceHolderDelegate,
		UserJsonPlaceholderMapper userJsonPlaceHolderMapper) {
		this.jsonPlaceholderDelegate = jsonPlaceHolderDelegate;
		this.userJsonPlaceholderMapper = userJsonPlaceHolderMapper;
	}

	@Override
	public List<User> findAll() {

		List<User> users = userJsonPlaceholderMapper
			.responseListToDomainList(jsonPlaceholderDelegate.findUsers());

		log.info("Obtained successful response from JSONPlaceholder resource with users info");

		return users;
	}

	@Override
	public User findById(int userId) {

		User user = userJsonPlaceholderMapper
			.responseToDomain(jsonPlaceholderDelegate.findUserById(userId));

		log.info(
			"Obtained successful response from JSONPlaceholder resource with the user with id {}",
			userId);

		return user;
	}
}
