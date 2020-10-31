package com.wchallenge.forum.application.service;

import com.wchallenge.forum.application.dto.album.PhotoDto;
import com.wchallenge.forum.application.mapper.AlbumAppMapper;
import com.wchallenge.forum.domain.service.AlbumService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AlbumAppService {

	private final AlbumService albumService;
	private final AlbumAppMapper albumAppMapper;

	public AlbumAppService(AlbumService albumService, AlbumAppMapper albumAppMapper) {
		this.albumService = albumService;
		this.albumAppMapper = albumAppMapper;
	}

	public List<PhotoDto> findAllPhotos() {
		return albumAppMapper.domainPhotosListToDtoPhotosList(albumService.findAllPhotos());
	}
}
