package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.delegate;

import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.album.AlbumResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.album.PhotoResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.user.UserResponse;
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
}
