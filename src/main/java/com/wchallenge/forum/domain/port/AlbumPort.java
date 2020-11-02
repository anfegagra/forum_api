package com.wchallenge.forum.domain.port;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.Photo;
import java.util.List;

public interface AlbumPort {

	/**
	 * Method that obtains a list of photos registered in JSONPlaceholder.
	 *
	 * @return The list of photos with their details.
	 */
	List<Photo> findAllPhotos();

	/**
	 * Method that obtains a list of albums registered in JSONPlaceholder.
	 *
	 * @return The list of albums with their details.
	 */
	List<Album> findAll();

	/**
	 * Method that obtains a list of albums registered in JSONPlaceholder filtered by user id.
	 *
	 * @param userId Id of the user to be used in the filter.
	 * @return The list of albums that belongs to the given user id.
	 */
	List<Album> findAlbumsByUserId(int userId);

	/**
	 * Method that obtains an album by id.
	 *
	 * @param albumId Id of the album to be used in the search.
	 * @return Album with its details.
	 */
	Album findById(int albumId);
}
