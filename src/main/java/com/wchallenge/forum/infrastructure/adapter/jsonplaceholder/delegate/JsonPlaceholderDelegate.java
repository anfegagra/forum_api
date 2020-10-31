package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.delegate;

import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.album.AlbumResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.album.PhotoResponse;
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

	public List<PhotoResponse> findAllPhotos() {
		return jsonPlaceholderFeignClient.findAllPhotos();
	}

	public List<AlbumResponse> findAllAlbums() {
		return jsonPlaceholderFeignClient.findAllAlbums();
	}
}
