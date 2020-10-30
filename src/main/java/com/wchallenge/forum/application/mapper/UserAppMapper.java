package com.wchallenge.forum.application.mapper;

import com.wchallenge.forum.application.dto.user.UserDto;
import com.wchallenge.forum.domain.model.user.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserAppMapper {

	List<UserDto> domainListToDtoList(List<User> user);

}
