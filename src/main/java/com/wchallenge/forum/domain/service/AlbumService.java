package com.wchallenge.forum.domain.service;

import com.wchallenge.forum.domain.exception.DataNotFoundException;
import com.wchallenge.forum.domain.exception.ForumNotificationCode;
import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.Photo;
import com.wchallenge.forum.domain.port.AlbumPort;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class AlbumService {

	private final AlbumPort albumPort;

	public AlbumService(AlbumPort albumPort) {
		this.albumPort = albumPort;
	}

	public List<Photo> findAllPhotos() {
		return albumPort.findAllPhotos();
	}

	public List<Album> findAll() {
		return albumPort.findAll();
	}

	public List<Album> findAlbumsByUserId(int userId) {

		List<Album> albums = albumPort.findAlbumsByUserId(userId);

		if (CollectionUtils.isEmpty(albums)) {
			throw new DataNotFoundException(ForumNotificationCode.DATA_NOT_FOUND);
		}

		return albums;
	}
}
