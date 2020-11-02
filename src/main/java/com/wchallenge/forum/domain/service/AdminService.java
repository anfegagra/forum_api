package com.wchallenge.forum.domain.service;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.SharedAlbum;
import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.domain.port.AdminPort;
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
}
