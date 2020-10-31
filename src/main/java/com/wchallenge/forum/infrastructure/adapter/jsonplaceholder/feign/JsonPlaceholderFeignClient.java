package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.feign;

import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.request.param.UserIdParam;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album.AlbumResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album.PhotoResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.user.UserResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${feign.jsonplaceholder.name}", url = "${feign.jsonplaceholder.url}")
public interface JsonPlaceholderFeignClient {

	@GetMapping(value = "${feign.jsonplaceholder.resource.users}")
	List<UserResponse> findUsers();

	@GetMapping(value = "${feign.jsonplaceholder.resource.photos}")
	List<PhotoResponse> findAllPhotos();

	@GetMapping(value = "${feign.jsonplaceholder.resource.albums}")
	List<AlbumResponse> findAllAlbums();

	@GetMapping(value = "${feign.jsonplaceholder.resource.albums}")
	List<AlbumResponse> findAlbumsByUserId(@SpringQueryMap UserIdParam userIdParam);

}
