package com.wchallenge.forum.infrastructure.controller;

import com.wchallenge.forum.application.dto.album.SharedAlbumRequestDto;
import com.wchallenge.forum.application.service.AdminAppService;
import com.wchallenge.forum.infrastructure.controller.dto.ApiResponseForumDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	private final AdminAppService adminAppService;

	public AdminController(AdminAppService adminAppService) {
		this.adminAppService = adminAppService;
	}

	@PostMapping("/albums/shared")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ApiResponseForumDto<Boolean> registerSharedAlbum(
		@RequestBody SharedAlbumRequestDto sharedAlbumRequestDto) {

		return new ApiResponseForumDto<>(adminAppService.registerSharedAlbum(sharedAlbumRequestDto),
			null);
	}
}
