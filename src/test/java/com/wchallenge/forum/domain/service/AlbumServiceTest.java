package com.wchallenge.forum.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.wchallenge.forum.domain.exception.DataNotFoundException;
import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.Photo;
import com.wchallenge.forum.domain.port.AlbumPort;
import com.wchallenge.forum.infrastructure.config.Messages;
import com.wchallenge.forum.infrastructure.config.Messages.MessageName;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

	@Mock
	private AlbumPort albumPort;

	@InjectMocks
	private AlbumService albumService;

	@Test
	void findAllPhotos() {

		// Arrange
		Photo photo = Photo.builder().albumId(1).id(1).title("test").url("url")
			.thumbnailUrl("thumbnailUrl").build();

		when(albumPort.findAllPhotos()).thenReturn(Collections.singletonList(photo));

		// Act
		List<Photo> photoList = albumService.findAllPhotos();

		// Assert
		assertThat(photoList).usingRecursiveFieldByFieldElementComparator()
			.isEqualTo(Collections.singletonList(photo));
	}

	@Test
	void findAll() {

		// Arrange
		Album album = Album.builder().userId(1).id(1).title("test").build();

		when(albumPort.findAll()).thenReturn(Collections.singletonList(album));

		// Act
		List<Album> albumsList = albumService.findAll();

		// Assert
		assertThat(albumsList).usingRecursiveFieldByFieldElementComparator()
			.isEqualTo(Collections.singletonList(album));
	}

	@Test
	void findAlbumsByUserId() {

		// Arrange
		int userId = 1;
		Album album = Album.builder().userId(userId).id(1).title("test").build();
		when(albumPort.findAlbumsByUserId(anyInt())).thenReturn(Collections.singletonList(album));

		// Act
		List<Album> albums = albumService.findAlbumsByUserId(userId);

		// Assert
		assertThat(albums).usingRecursiveFieldByFieldElementComparator()
			.isEqualTo(Collections.singletonList(album));
	}

	@Test
	void findAlbumsByUserIdWithEmptyAlbumList() {

		// Arrange
		int userId = 1;
		when(albumPort.findAlbumsByUserId(anyInt())).thenReturn(Collections.emptyList());

		// Act - Assert
		assertThatThrownBy(() -> albumService.findAlbumsByUserId(userId))
			.isInstanceOf(DataNotFoundException.class)
			.hasMessage(Messages.getMessage(MessageName.DATA_NOT_FOUND));
	}

}