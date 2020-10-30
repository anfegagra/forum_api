package com.wchallenge.forum.application.service;

import com.wchallenge.forum.application.dto.user.UserDto;
import com.wchallenge.forum.application.mapper.UserAppMapper;
import com.wchallenge.forum.domain.service.UserService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserAppService {

	private final UserService userService;
	private final UserAppMapper userAppMapper;

	public UserAppService(UserService userService, UserAppMapper userAppMapper) {
		this.userService = userService;
		this.userAppMapper = userAppMapper;
	}

	public List<UserDto> findAll() {

		return userAppMapper.domainListToDtoList(userService.findAll());
	}

}
