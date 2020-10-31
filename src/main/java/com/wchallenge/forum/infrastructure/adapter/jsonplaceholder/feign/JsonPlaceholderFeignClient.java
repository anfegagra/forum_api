package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.feign;

import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.album.PhotoResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.user.UserResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${feign.jsonplaceholder.name}", url = "${feign.jsonplaceholder.url}")
public interface JsonPlaceholderFeignClient {

	@GetMapping(value = "${feign.jsonplaceholder.resource.users}")
	List<UserResponse> findUsers();

	@GetMapping(value = "${feign.jsonplaceholder.resource.photos}")
	List<PhotoResponse> findAllPhotos();

}
