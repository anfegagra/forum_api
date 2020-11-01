package com.wchallenge.forum.application.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.wchallenge.forum.application.dto.album.AlbumDto;
import com.wchallenge.forum.application.dto.album.PhotoDto;
import com.wchallenge.forum.application.mapper.AlbumAppMapper;
import com.wchallenge.forum.domain.service.AlbumService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AlbumAppServiceTest {

	@Mock
	private AlbumService albumService;

	@Mock
	private AlbumAppMapper albumAppMapper;

	@InjectMocks
	private AlbumAppService albumAppService;

	@Test
	void findAlbumsByUserId() {

		// Arrange
		Integer userId = 1;

		// Act
		List<AlbumDto> albumDtoList = albumAppService.findAll(userId);

		// Assert
		verify(albumService, times(1)).findAlbumsByUserId(userId);
		verify(albumService, times(0)).findAll();
	}

	@Test
	void findAll() {

		// Act
		List<AlbumDto> albumDtoList = albumAppService.findAll(null);

		// Assert
		verify(albumService, times(0)).findAlbumsByUserId(anyInt());
		verify(albumService, times(1)).findAll();
	}

	@Test
	void findAllPhotos() {

		// Act
		List<PhotoDto> photoList = albumAppService.findAllPhotos(null);

		// Assert
		verify(albumService, times(0)).findPhotosByUserId(anyInt());
		verify(albumService, times(1)).findAllPhotos();
	}

	@Test
	void findPhotosByUserId() {

		// Arrange
		Integer userId = 1;

		// Act
		List<PhotoDto> photoList = albumAppService.findAllPhotos(userId);

		// Assert
		verify(albumService, times(1)).findPhotosByUserId(userId);
		verify(albumService, times(0)).findAllPhotos();
	}

}