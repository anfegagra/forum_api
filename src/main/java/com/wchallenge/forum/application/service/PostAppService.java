package com.wchallenge.forum.application.service;

import com.wchallenge.forum.application.dto.post.CommentDto;
import com.wchallenge.forum.application.mapper.PostAppMapper;
import com.wchallenge.forum.domain.service.PostService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostAppService {

	private final PostService postService;
	private final PostAppMapper postAppMapper;

	public PostAppService(PostService postService, PostAppMapper postAppMapper) {
		this.postService = postService;
		this.postAppMapper = postAppMapper;
	}

	public List<CommentDto> findComments(String name, Integer userId) {

		if (StringUtils.isNotEmpty(name)) {

			log.info("Starting to search comments from external resources filtered by name {}",
				name);

			return postAppMapper
				.domainCommentsListToDtoPhotosList(postService.findCommentsByName(name));

		} else if (userId != null) {

			log.info("Starting to search comments from external resources filtered by user id {}",
				userId);

			return postAppMapper
				.domainCommentsListToDtoPhotosList(postService.findCommentsByUserId(userId));
		}

		return postAppMapper
			.domainCommentsListToDtoPhotosList(postService.findComments());
	}
}
