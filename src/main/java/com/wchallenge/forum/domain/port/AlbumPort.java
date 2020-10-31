package com.wchallenge.forum.domain.port;

import com.wchallenge.forum.domain.model.album.Photo;
import java.util.List;

public interface AlbumPort {

	/**
	 * Method that obtains a list of photos registered in JSONPlaceholder
	 *
	 * @return The list of photos with their details
	 */
	List<Photo> findAllPhotos();
}
