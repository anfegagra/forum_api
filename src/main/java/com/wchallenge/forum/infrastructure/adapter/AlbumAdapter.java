package com.wchallenge.forum.infrastructure.adapter;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.Photo;
import com.wchallenge.forum.domain.port.AlbumPort;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.delegate.JsonPlaceholderDelegate;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.mapper.AlbumJsonPlaceholderMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlbumAdapter implements AlbumPort {

	private final JsonPlaceholderDelegate jsonPlaceholderDelegate;
	private final AlbumJsonPlaceholderMapper albumJsonPlaceholderMapper;

	public AlbumAdapter(
		JsonPlaceholderDelegate jsonPlaceHolderDelegate,
		AlbumJsonPlaceholderMapper albumJsonPlaceholderMapper) {
		this.jsonPlaceholderDelegate = jsonPlaceHolderDelegate;
		this.albumJsonPlaceholderMapper = albumJsonPlaceholderMapper;
	}

	@Override
	public List<Photo> findAllPhotos() {

		List<Photo> photos = albumJsonPlaceholderMapper
			.responsePhotosListToDomainPhotosList(jsonPlaceholderDelegate.findAllPhotos());

		log.info("Obtained successful response from JSONPlaceholder resource with photos info");

		return photos;
	}

	@Override
	public List<Album> findAll() {

		List<Album> albums = albumJsonPlaceholderMapper
			.responseAlbumsListToDomainAlbumsList(jsonPlaceholderDelegate.findAllAlbums());

		log.info("Obtained successful response from JSONPlaceholder resource with albums info");

		return albums;
	}

	@Override
	public List<Album> findAlbumsByUserId(int userId) {

		List<Album> albums = albumJsonPlaceholderMapper
			.responseAlbumsListToDomainAlbumsList(
				jsonPlaceholderDelegate.findAlbumsByUserId(userId));

		log.info(
			"Obtained successful response from JSONPlaceholder resource with albums info for the user with id {}",
			userId);

		return albums;
	}

}
