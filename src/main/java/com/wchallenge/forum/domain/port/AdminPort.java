package com.wchallenge.forum.domain.port;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.PermissionType;
import com.wchallenge.forum.domain.model.album.SharedAlbum;
import com.wchallenge.forum.domain.model.user.User;

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
	 * Method that updates the permission of an user for a given album.
	 *
	 * @param sharedAlbum Object with info about the album id, user id and the permission to apply.
	 * @return Boolean that indicates if the permission was applied successfully.
	 */
	boolean updateUserPermissionInASharedAlbum(SharedAlbum sharedAlbum);
}
