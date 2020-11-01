package com.wchallenge.forum.application.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.wchallenge.forum.application.dto.post.CommentDto;
import com.wchallenge.forum.application.mapper.PostAppMapper;
import com.wchallenge.forum.domain.service.PostService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostAppServiceTest {

	@Mock
	private PostService postService;

	@Mock
	private PostAppMapper postAppMapper;

	@InjectMocks
	private PostAppService postAppService;

	@Test
	void findComments() {

		// Act
		List<CommentDto> comments = postAppService.findComments(null, null);

		// Assert
		verify(postService, times(1)).findComments();
		verify(postService, times(0)).findCommentsByName(any());
		verify(postService, times(0)).findCommentsByUserId(anyInt());
	}

	@Test
	void findCommentsByName() {

		// Act
		List<CommentDto> comments = postAppService.findComments("test", null);

		// Assert
		verify(postService, times(0)).findComments();
		verify(postService, times(1)).findCommentsByName(any());
		verify(postService, times(0)).findCommentsByUserId(anyInt());
	}

	@Test
	void findcommentsByUserId() {

		// Act
		List<CommentDto> comments = postAppService.findComments(null, 1);

		// Assert
		verify(postService, times(0)).findComments();
		verify(postService, times(0)).findCommentsByName(any());
		verify(postService, times(1)).findCommentsByUserId(anyInt());
	}

}