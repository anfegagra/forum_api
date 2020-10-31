package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.delegate;

import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.request.param.UserIdParam;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album.AlbumResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album.PhotoResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.user.UserResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.feign.JsonPlaceholderFeignClient;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonPlaceholderDelegate {

	private final JsonPlaceholderFeignClient jsonPlaceholderFeignClient;

	public JsonPlaceholderDelegate(
		JsonPlaceholderFeignClient jsonPlaceholderFeignClient) {
		this.jsonPlaceholderFeignClient = jsonPlaceholderFeignClient;
	}

	public List<UserResponse> findUsers() {

		log.info("Connecting to JSONPlaceholder resource to get info about users...");

		return jsonPlaceholderFeignClient.findUsers();
	}

	public List<PhotoResponse> findAllPhotos() {

		log.info("Connecting to JSONPlaceholder resource to get info about photos...");

		return jsonPlaceholderFeignClient.findAllPhotos();
	}

	public List<AlbumResponse> findAllAlbums() {

		log.info("Connecting to JSONPlaceholder resource to get info about albums...");

		return jsonPlaceholderFeignClient.findAllAlbums();
	}

	public List<AlbumResponse> findAlbumsByUserId(int userId) {

		log.info(
			"Connecting to JSONPlaceholder resource to get info about albums for the user with id {}",
			userId);

		return jsonPlaceholderFeignClient.findAlbumsByUserId(new UserIdParam(userId));
	}
}
