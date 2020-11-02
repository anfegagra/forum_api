package com.wchallenge.forum.domain.port;

import com.wchallenge.forum.domain.model.album.Album;
import java.util.List;

public interface AlbumRepositoryPort {

	/**
	 * Method that finds a list of albums shared with the given user id.
	 *
	 * @param userId Id of the user to be used in the search.
	 * @return The list of albums shared with the given user id.
	 */
	List<Album> findByUserId(int userId);
}
