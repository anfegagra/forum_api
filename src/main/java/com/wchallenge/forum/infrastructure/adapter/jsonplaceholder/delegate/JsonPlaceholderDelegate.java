package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.delegate;

import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.request.param.CommentNameParam;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.request.param.IdParam;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.request.param.UserIdParam;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album.AlbumResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album.PhotoResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.post.CommentResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.post.PostResponse;
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

	public UserResponse findUserById(int userId) {

		log.info("Connecting to JSONPlaceholder resource to get info about the user with id {}",
			userId);

		return jsonPlaceholderFeignClient.findUserById(new IdParam(userId)).stream().findFirst()
			.orElse(null);
	}

	public List<PhotoResponse> findAllPhotos() {

		log.info("Connecting to JSONPlaceholder resource to get info about photos...");

		return jsonPlaceholderFeignClient.findAllPhotos();
	}

	public List<AlbumResponse> findAllAlbums() {

		log.info("Connecting to JSONPlaceholder resource to get info about albums...");

		return jsonPlaceholderFeignClient.findAllAlbums();
	}

	public AlbumResponse findAlbumById(int albumId) {

		log.info("Connecting to JSONPlaceholder resource to get info about album with id {}",
			albumId);

		return jsonPlaceholderFeignClient.findAlbumById(new IdParam(albumId)).stream().findFirst()
			.orElse(null);
	}

	public List<AlbumResponse> findAlbumsByUserId(int userId) {

		log.info(
			"Connecting to JSONPlaceholder resource to get info about albums for the user with id {}",
			userId);

		return jsonPlaceholderFeignClient.findAlbumsByUserId(new UserIdParam(userId));
	}

	public List<CommentResponse> findComments() {

		log.info("Connecting to JSONPlaceholder resource to get info about comments...");

		return jsonPlaceholderFeignClient.findComments();
	}

	public List<CommentResponse> findCommentsByName(String name) {

		log.info(
			"Connecting to JSONPlaceholder resource to get info about comments having the name: {}",
			name);

		return jsonPlaceholderFeignClient.findCommentsByName(new CommentNameParam(name));
	}

	public List<PostResponse> findPostsByUserId(int userId) {

		log.info(
			"Connecting to JSONPlaceholder resource to get info about posts for the user with id {}",
			userId);

		return jsonPlaceholderFeignClient.findPostsByUserId(new UserIdParam(userId));
	}
}
