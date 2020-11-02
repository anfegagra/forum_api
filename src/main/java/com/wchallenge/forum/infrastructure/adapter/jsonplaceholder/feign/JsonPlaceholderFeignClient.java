package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.feign;

import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.request.param.CommentNameParam;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.request.param.IdParam;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album.AlbumResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album.PhotoResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.post.CommentResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.post.PostResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.user.UserResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${feign.jsonplaceholder.name}", url = "${feign.jsonplaceholder.url}")
public interface JsonPlaceholderFeignClient {

	@GetMapping(value = "${feign.jsonplaceholder.resource.users}")
	List<UserResponse> findUsers();

	@GetMapping(value = "${feign.jsonplaceholder.resource.users}")
	List<UserResponse> findUserById(@SpringQueryMap IdParam idParam);

	@GetMapping(value = "${feign.jsonplaceholder.resource.photos}")
	List<PhotoResponse> findAllPhotos();

	@GetMapping(value = "${feign.jsonplaceholder.resource.albums}")
	List<AlbumResponse> findAllAlbums();

	@GetMapping(value = "${feign.jsonplaceholder.resource.albums}")
	List<AlbumResponse> findAlbumById(@SpringQueryMap IdParam idParam);

	@GetMapping(value = "${feign.jsonplaceholder.resource.albums}")
	List<AlbumResponse> findAlbumsByUserId(@SpringQueryMap IdParam idParam);

	@GetMapping(value = "${feign.jsonplaceholder.resource.comments}")
	List<CommentResponse> findComments();

	@GetMapping(value = "${feign.jsonplaceholder.resource.comments}")
	List<CommentResponse> findCommentsByName(@SpringQueryMap CommentNameParam commentNameParam);

	@GetMapping(value = "${feign.jsonplaceholder.resource.posts}")
	List<PostResponse> findPostsByUserId(@SpringQueryMap IdParam idParam);

}
