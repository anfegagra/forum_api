package com.wchallenge.forum.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.wchallenge.forum.domain.exception.DataNotFoundException;
import com.wchallenge.forum.domain.model.post.Comment;
import com.wchallenge.forum.domain.model.post.Post;
import com.wchallenge.forum.domain.port.PostPort;
import com.wchallenge.forum.infrastructure.config.Messages;
import com.wchallenge.forum.infrastructure.config.Messages.MessageName;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

	@Mock
	private PostPort postPort;

	@InjectMocks
	private PostService postService;

	@Test
	void findComments() {

		// Arrange
		Comment comment1 = Comment.builder().postId(1).id(1).name("test name")
			.email("test@test.com")
			.body("body").build();
		Comment comment2 = Comment.builder().postId(1).id(2).name("test name")
			.email("test@test.com")
			.body("body").build();
		when(postPort.findComments()).thenReturn(Arrays.asList(comment1, comment2));

		// Act
		List<Comment> comments = postService.findComments();

		// Assert
		assertThat(comments).usingRecursiveFieldByFieldElementComparator()
			.isEqualTo(Arrays.asList(comment1, comment2));
	}

	@Test
	void findCommentsByName() {

		// Arrange
		Comment comment1 = Comment.builder().postId(1).id(1).name("test name1")
			.email("test@test.com")
			.body("body").build();
		Comment comment2 = Comment.builder().postId(1).id(2).name("test name1")
			.email("test@test.com")
			.body("body").build();
		when(postPort.findCommentsByName(any())).thenReturn(Arrays.asList(comment1, comment2));

		// Act
		List<Comment> comments = postService.findCommentsByName(comment1.getName());

		// Assert
		assertThat(comments).usingRecursiveFieldByFieldElementComparator()
			.isEqualTo(Arrays.asList(comment1, comment2));
	}

	@Test
	void findCommentsByUserId() {

		// Arrange
		Post post1 = Post.builder().userId(1).id(1).title("title").body("body").build();
		Post post2 = Post.builder().userId(2).id(2).title("title").body("body").build();
		when(postPort.findPostsByUserId(anyInt())).thenReturn(Arrays.asList(post1, post2));

		Comment comment1 = Comment.builder().postId(post1.getId()).id(1).name("test name1")
			.email("test@test.com")
			.body("body").build();
		Comment comment2 = Comment.builder().postId(3).id(2).name("test name2")
			.email("test@test.com")
			.body("body").build();
		when(postPort.findComments()).thenReturn(Arrays.asList(comment1, comment2));

		// Act
		List<Comment> comments = postService.findCommentsByUserId(post1.getUserId());

		// Assert
		assertThat(comments).usingRecursiveFieldByFieldElementComparator()
			.isEqualTo(Collections.singletonList(comment1));
	}

	@Test
	void findCommentsByUserIdWithCommentsListEmpty() {

		// Arrange
		when(postPort.findComments()).thenReturn(Collections.emptyList());

		// Act - Assert
		assertThatThrownBy(() -> postService.findCommentsByUserId(1))
			.isInstanceOf(DataNotFoundException.class)
			.hasMessage(Messages.getMessage(MessageName.DATA_NOT_FOUND));
	}
}