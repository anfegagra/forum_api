package com.wchallenge.forum.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wchallenge.forum.application.dto.post.CommentDto;
import com.wchallenge.forum.application.service.PostAppService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class PostControllerTest {

	private static PostAppService postAppService;

	private static MockMvc mockMvc;

	@BeforeAll
	static void setUp() {
		postAppService = mock(PostAppService.class);
		PostController postController = new PostController(postAppService);

		mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
	}

	@Test
	void findComments() throws Exception {

		// Arrange
		CommentDto commentDto = CommentDto.builder().postId(1).id(1).name("test").build();

		when(postAppService.findComments(any(), any()))
			.thenReturn(Collections.singletonList(commentDto));

		// Act - Assert
		mockMvc.perform(get("/api/v1/posts/comments"))
			.andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("data[0].postId").value(commentDto.getPostId()))
			.andExpect(jsonPath("data[0].id").value(commentDto.getId()))
			.andExpect(jsonPath("data[0].name").value(commentDto.getName()));
	}
}