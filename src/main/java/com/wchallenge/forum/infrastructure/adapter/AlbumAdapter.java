package com.wchallenge.forum.infrastructure.adapter;

import com.wchallenge.forum.domain.model.album.Photo;
import com.wchallenge.forum.domain.port.AlbumPort;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.delegate.JsonPlaceholderDelegate;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.mapper.AlbumJsonPlaceholderMapper;
import java.util.List;
import org.springframework.stereotype.Component;

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

		return albumJsonPlaceholderMapper
			.responsePhotosListToDomainPhotosList(jsonPlaceholderDelegate.findAllPhotos());
	}

}
