package com.wchallenge.forum.application.service;

import com.wchallenge.forum.application.dto.user.UserDto;
import com.wchallenge.forum.application.mapper.UserAppMapper;
import com.wchallenge.forum.domain.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserAppService {

	private final UserService userService;
	private final UserAppMapper userAppMapper;

	public UserAppService(UserService userService, UserAppMapper userAppMapper) {
		this.userService = userService;
		this.userAppMapper = userAppMapper;
	}

	public List<UserDto> findAll() {

		log.info("Starting to search users from external resources...");

		return userAppMapper.domainListToDtoList(userService.findAll());
	}

}
