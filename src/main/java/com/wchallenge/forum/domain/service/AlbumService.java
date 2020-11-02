package com.wchallenge.forum.domain.service;

import com.wchallenge.forum.domain.exception.DataNotFoundException;
import com.wchallenge.forum.domain.exception.ForumNotificationCode;
import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.Photo;
import com.wchallenge.forum.domain.port.AlbumPort;
import com.wchallenge.forum.domain.port.AlbumRepositoryPort;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

	private final AlbumPort albumPort;
	private final AlbumRepositoryPort albumRepositoryPort;

	public AlbumService(AlbumPort albumPort, AlbumRepositoryPort albumRepositoryPort) {
		this.albumPort = albumPort;
		this.albumRepositoryPort = albumRepositoryPort;
	}

	public List<Photo> findAllPhotos() {
		return albumPort.findAllPhotos();
	}

	public List<Album> findAll() {
		return albumPort.findAll();
	}

	public Album findById(int albumId) {

		Album album = albumPort.findById(albumId);

		if (album == null) {
			throw new DataNotFoundException(ForumNotificationCode.DATA_NOT_FOUND);
		}

		return album;
	}

	public List<Album> findAlbumsByUserId(int userId) {

		List<Album> albums = albumPort.findAlbumsByUserId(userId);

		addSharedAlbumsIfApplies(userId, albums);

		if (albums.isEmpty()) {
			throw new DataNotFoundException(ForumNotificationCode.DATA_NOT_FOUND);
		}

		return albums;
	}

	private void addSharedAlbumsIfApplies(int userId, List<Album> albums) {

		List<Album> sharedAlbums = albumRepositoryPort.findByUserId(userId);
		if (!sharedAlbums.isEmpty()) {
			albums.addAll(sharedAlbums);
		}
	}

	public List<Photo> findPhotosByUserId(int userId) {

		List<Album> albums = findAlbumsByUserId(userId);

		List<Photo> photos = findAllPhotos().stream()
			.filter(photo -> albums.stream().anyMatch(album -> album.getId() == photo.getAlbumId()))
			.collect(Collectors.toList());

		if (photos.isEmpty()) {
			throw new DataNotFoundException(ForumNotificationCode.DATA_NOT_FOUND);
		}

		return photos;
	}
}
