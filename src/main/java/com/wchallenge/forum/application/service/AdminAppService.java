package com.wchallenge.forum.application.service;

import com.wchallenge.forum.application.dto.album.SharedAlbumRequestDto;
import com.wchallenge.forum.application.mapper.AdminAppMapper;
import com.wchallenge.forum.domain.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class AdminAppService {

	private final AdminService adminService;
	private final AdminAppMapper adminAppMapper;

	public AdminAppService(AdminService adminService, AdminAppMapper adminAppMapper) {
		this.adminService = adminService;
		this.adminAppMapper = adminAppMapper;
	}

	@Transactional
	public boolean registerSharedAlbum(SharedAlbumRequestDto sharedAlbumRequestDto) {

		log.info(
			"Starting to register a shared album with album id {} and user id {} with permission {}",
			sharedAlbumRequestDto.getAlbumId(), sharedAlbumRequestDto.getUserId(),
			sharedAlbumRequestDto.getPermission());

		return adminService.registerSharedAlbum(adminAppMapper.dtoToDomain(sharedAlbumRequestDto));
	}

	public boolean updateUserPermissionInASHaredAlbum(SharedAlbumRequestDto sharedAlbumRequestDto) {

		log.info(
			"Starting to update the permission with {} of the user id {} for the album with id {}",
			sharedAlbumRequestDto.getPermission(),
			sharedAlbumRequestDto.getUserId(),
			sharedAlbumRequestDto.getAlbumId());

		return adminService
			.updateUserPermissionInASharedAlbum(adminAppMapper.dtoToDomain(sharedAlbumRequestDto));
	}

}
