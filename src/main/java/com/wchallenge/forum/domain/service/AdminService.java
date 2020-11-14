package com.wchallenge.forum.domain.service;

import com.wchallenge.forum.domain.exception.DataNotFoundException;
import com.wchallenge.forum.domain.exception.ForumNotificationCode;
import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.PermissionType;
import com.wchallenge.forum.domain.model.album.SharedAlbum;
import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.domain.port.AdminPort;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	private final AdminPort adminPort;
	private final UserService userService;
	private final AlbumService albumService;

	public AdminService(AdminPort adminPort,
		UserService userService, AlbumService albumService) {
		this.adminPort = adminPort;
		this.userService = userService;
		this.albumService = albumService;
	}

	public boolean registerSharedAlbum(SharedAlbum sharedAlbum) {

		User user = userService.findById(sharedAlbum.getUserId());
		Album album = albumService.findById(sharedAlbum.getAlbumId());

		return adminPort.registerSharedAlbum(user, album, sharedAlbum.getPermission());
	}

	public List<User> findUsersByPermissionForAGivenAlbum(PermissionType permission, int albumId) {

		List<User> users = userService.findAll();

		List<Long> usersWithSharedAlbums = adminPort
			.findUsersByPermissionForAGivenAlbum(permission, albumId);

		List<User> usersWithGivenPermission = users.stream()
			.filter(user -> usersWithSharedAlbums.stream()
				.anyMatch(userIdWithSharedAlbum -> userIdWithSharedAlbum == user.getId()))
			.collect(Collectors.toList());

		if (usersWithGivenPermission.isEmpty()) {
			throw new DataNotFoundException(ForumNotificationCode.DATA_NOT_FOUND);
		}

		return usersWithGivenPermission;
	}
}
