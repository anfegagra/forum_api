package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.mapper;

import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.user.UserResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserJsonPlaceholderMapper {

	List<User> responseListToDomainList(List<UserResponse> userResponseList);
}
