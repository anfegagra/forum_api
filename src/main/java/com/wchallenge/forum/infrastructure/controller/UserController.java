package com.wchallenge.forum.infrastructure.controller;

import com.wchallenge.forum.application.dto.user.UserDto;
import com.wchallenge.forum.application.service.UserAppService;
import com.wchallenge.forum.infrastructure.controller.dto.ApiResponseForumDto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserAppService userAppService;

	public UserController(UserAppService userAppService) {
		this.userAppService = userAppService;
	}

	@GetMapping
	public ApiResponseForumDto<List<UserDto>> findAll() {

		return new ApiResponseForumDto<>(userAppService.findAll(), null);
	}

}
