package com.wchallenge.forum.application.mapper;

import com.wchallenge.forum.application.dto.post.CommentDto;
import com.wchallenge.forum.domain.model.post.Comment;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PostAppMapper {

	List<CommentDto> domainCommentsListToDtoPhotosList(List<Comment> commentList);
}
