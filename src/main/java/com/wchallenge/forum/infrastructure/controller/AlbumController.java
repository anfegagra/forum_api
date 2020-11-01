package com.wchallenge.forum.infrastructure.controller;

import com.wchallenge.forum.application.dto.album.AlbumDto;
import com.wchallenge.forum.application.dto.album.PhotoDto;
import com.wchallenge.forum.application.service.AlbumAppService;
import com.wchallenge.forum.infrastructure.controller.dto.ApiResponseForumDto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

	private final AlbumAppService albumAppService;

	public AlbumController(AlbumAppService albumAppService) {
		this.albumAppService = albumAppService;
	}

	@GetMapping("/photos")
	public ApiResponseForumDto<List<PhotoDto>> findAllPhotos(
		@RequestParam(value = "userId", required = false) Integer userId) {

		return new ApiResponseForumDto<>(albumAppService.findAllPhotos(userId), null);
	}

	@GetMapping
	public ApiResponseForumDto<List<AlbumDto>> findAll(
		@RequestParam(value = "userId", required = false) Integer userId) {

		return new ApiResponseForumDto<>(albumAppService.findAll(userId), null);
	}

}
