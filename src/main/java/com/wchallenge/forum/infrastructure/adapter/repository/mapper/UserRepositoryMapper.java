package com.wchallenge.forum.infrastructure.adapter.repository.mapper;

import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserRepositoryMapper {

	UserEntity domainToEntity(User user);
}
