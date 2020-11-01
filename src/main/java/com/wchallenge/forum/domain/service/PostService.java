package com.wchallenge.forum.domain.service;

import com.wchallenge.forum.domain.exception.DataNotFoundException;
import com.wchallenge.forum.domain.exception.ForumNotificationCode;
import com.wchallenge.forum.domain.model.post.Comment;
import com.wchallenge.forum.domain.model.post.Post;
import com.wchallenge.forum.domain.port.PostPort;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	private final PostPort postPort;

	public PostService(PostPort postPort) {
		this.postPort = postPort;
	}

	public List<Comment> findComments() {
		return postPort.findComments();
	}

	public List<Comment> findCommentsByName(String name) {

		return postPort.findCommentsByName(name);
	}

	public List<Comment> findCommentsByUserId(int userId) {

		List<Post> posts = postPort.findPostsByUserId(userId);

		List<Comment> comments = findComments().stream()
			.filter(comment -> posts.stream().anyMatch(post -> post.getId() == comment.getPostId()))
			.collect(
				Collectors.toList());

		if (comments.isEmpty()) {
			throw new DataNotFoundException(ForumNotificationCode.DATA_NOT_FOUND);
		}

		return comments;
	}
}
