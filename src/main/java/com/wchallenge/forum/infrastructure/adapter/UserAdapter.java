package com.wchallenge.forum.infrastructure.adapter;

import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.domain.port.UserPort;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.delegate.JsonPlaceholderDelegate;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.mapper.UserJsonPlaceholderMapper;
import java.util.List;
import org.springframework.stereotype.Component;

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

		return userJsonPlaceholderMapper
			.responseListToDomainList(jsonPlaceholderDelegate.findUsers());
	}
}
