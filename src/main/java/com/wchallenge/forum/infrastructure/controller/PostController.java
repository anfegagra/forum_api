package com.wchallenge.forum.infrastructure.controller;

import com.wchallenge.forum.application.dto.post.CommentDto;
import com.wchallenge.forum.application.service.PostAppService;
import com.wchallenge.forum.infrastructure.controller.dto.ApiResponseForumDto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

	private final PostAppService postAppService;

	public PostController(PostAppService postAppService) {
		this.postAppService = postAppService;
	}

	@GetMapping("/comments")
	public ApiResponseForumDto<List<CommentDto>> findComments(
		@RequestParam(value = "name", required = false) String name,
		@RequestParam(value = "userId", required = false) Integer userId) {

		return new ApiResponseForumDto<>(postAppService.findComments(name, userId), null);
	}

}
