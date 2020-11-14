package com.wchallenge.forum.domain.port;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.PermissionType;
import com.wchallenge.forum.domain.model.user.User;
import java.util.List;

public interface AdminPort {

	/**
	 * Method that registers a shared album with an user and his corresponding permission.
	 *
	 * @param user       Info about the user.
	 * @param album      Info about the album.
	 * @param permission Permission granted to the user for the given album.
	 * @return Boolean that indicates if the shared album was registered successfully.
	 */
	boolean registerSharedAlbum(User user, Album album, PermissionType permission);

	/**
	 * Method that gets a list of users with a specified permission for a given album id
	 *
	 * @param permission Given permission to be used in the search.
	 * @param albumId    Given album id to be used in the search.
	 * @return a list of users that have the permission for the given album id.
	 */
	List<Long> findUsersByPermissionForAGivenAlbum(PermissionType permission, int albumId);
}
