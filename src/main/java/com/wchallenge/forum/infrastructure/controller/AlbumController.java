package com.wchallenge.forum.infrastructure.controller;

import com.wchallenge.forum.application.dto.album.PhotoDto;
import com.wchallenge.forum.application.service.AlbumAppService;
import com.wchallenge.forum.infrastructure.controller.dto.ApiResponseForumDto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

	private final AlbumAppService albumAppService;

	public AlbumController(AlbumAppService albumAppService) {
		this.albumAppService = albumAppService;
	}

	@GetMapping("/photos")
	public ApiResponseForumDto<List<PhotoDto>> findAllPhotos() {

		return new ApiResponseForumDto<>(albumAppService.findAllPhotos(), null);
	}

}
