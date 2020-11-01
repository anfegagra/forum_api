package com.wchallenge.forum.infrastructure.adapter;

import com.wchallenge.forum.domain.model.post.Comment;
import com.wchallenge.forum.domain.model.post.Post;
import com.wchallenge.forum.domain.port.PostPort;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.delegate.JsonPlaceholderDelegate;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.mapper.PostJsonPlaceholderMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostAdapter implements PostPort {

	private final JsonPlaceholderDelegate jsonPlaceholderDelegate;
	private final PostJsonPlaceholderMapper postJsonPlaceholderMapper;

	public PostAdapter(
		JsonPlaceholderDelegate jsonPlaceholderDelegate,
		PostJsonPlaceholderMapper postJsonPlaceholderMapper) {
		this.jsonPlaceholderDelegate = jsonPlaceholderDelegate;
		this.postJsonPlaceholderMapper = postJsonPlaceholderMapper;
	}

	@Override
	public List<Comment> findComments() {

		return postJsonPlaceholderMapper
			.responseCommentsListToDomainCommentsList(jsonPlaceholderDelegate.findComments());
	}

	@Override
	public List<Comment> findCommentsByName(String name) {

		return postJsonPlaceholderMapper
			.responseCommentsListToDomainCommentsList(
				jsonPlaceholderDelegate.findCommentsByName(name));
	}

	@Override
	public List<Post> findPostsByUserId(int userId) {

		return postJsonPlaceholderMapper
			.responsePostsListToDomainPostsList(jsonPlaceholderDelegate.findPostsByUserId(userId));
	}
}
