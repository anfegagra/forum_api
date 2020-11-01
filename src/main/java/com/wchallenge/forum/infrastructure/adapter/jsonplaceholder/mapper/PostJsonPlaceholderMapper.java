package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.mapper;

import com.wchallenge.forum.domain.model.post.Comment;
import com.wchallenge.forum.domain.model.post.Post;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.post.CommentResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.post.PostResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PostJsonPlaceholderMapper {

	List<Comment> responseCommentsListToDomainCommentsList(
		List<CommentResponse> commentResponseList);

	List<Post> responsePostsListToDomainPostsList(List<PostResponse> postResponseList);
}
