package com.wchallenge.forum.application.service;

import com.wchallenge.forum.application.dto.album.AlbumDto;
import com.wchallenge.forum.application.dto.album.PhotoDto;
import com.wchallenge.forum.application.mapper.AlbumAppMapper;
import com.wchallenge.forum.domain.service.AlbumService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlbumAppService {

	private final AlbumService albumService;
	private final AlbumAppMapper albumAppMapper;

	public AlbumAppService(AlbumService albumService, AlbumAppMapper albumAppMapper) {
		this.albumService = albumService;
		this.albumAppMapper = albumAppMapper;
	}

	public List<PhotoDto> findAllPhotos(Integer userId) {

		if (userId != null) {

			log.info("Starting to search photos from external resources for user with id {}",
				userId);

			return albumAppMapper
				.domainPhotosListToDtoPhotosList(albumService.findPhotosByUserId(userId));
		}

		log.info("Starting to search photos from external resources...");

		return albumAppMapper.domainPhotosListToDtoPhotosList(albumService.findAllPhotos());
	}

	public List<AlbumDto> findAll(Integer userId) {

		if (userId != null) {

			log.info("Starting to search albums from external resources for user with id {}",
				userId);

			return albumAppMapper
				.domainAlbumListToDtoAlbumList(albumService.findAlbumsByUserId(userId));
		}

		log.info("Starting to search albums from external resources...");

		return albumAppMapper.domainAlbumListToDtoAlbumList(albumService.findAll());
	}
}
