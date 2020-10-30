package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.delegate;

import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.user.UserResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.feign.JsonPlaceholderFeignClient;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JsonPlaceholderDelegate {

	private final JsonPlaceholderFeignClient jsonPlaceholderFeignClient;

	public JsonPlaceholderDelegate(
		JsonPlaceholderFeignClient jsonPlaceholderFeignClient) {
		this.jsonPlaceholderFeignClient = jsonPlaceholderFeignClient;
	}

	public List<UserResponse> findUsers() {
		return jsonPlaceholderFeignClient.findUsers();
	}
}
