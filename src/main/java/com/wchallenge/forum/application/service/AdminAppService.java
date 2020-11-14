package com.wchallenge.forum.application.service;

import com.wchallenge.forum.application.dto.album.SharedAlbumRequestDto;
import com.wchallenge.forum.application.dto.user.UserDto;
import com.wchallenge.forum.application.mapper.AdminAppMapper;
import com.wchallenge.forum.application.mapper.UserAppMapper;
import com.wchallenge.forum.domain.model.album.PermissionType;
import com.wchallenge.forum.domain.service.AdminService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminAppService {

	private final AdminService adminService;
	private final AdminAppMapper adminAppMapper;
	private final UserAppMapper userAppMapper;

	public AdminAppService(AdminService adminService, AdminAppMapper adminAppMapper,
		UserAppMapper userAppMapper) {
		this.adminService = adminService;
		this.adminAppMapper = adminAppMapper;
		this.userAppMapper = userAppMapper;
	}

	public boolean registerSharedAlbum(SharedAlbumRequestDto sharedAlbumRequestDto) {

		log.info(
			"Starting to register a shared album with album id {} and user id {} with permission {}",
			sharedAlbumRequestDto.getAlbumId(), sharedAlbumRequestDto.getUserId(),
			sharedAlbumRequestDto.getPermission());

		return adminService.registerSharedAlbum(adminAppMapper.dtoToDomain(sharedAlbumRequestDto));
	}

	public List<UserDto> findUsersByPermissionForAGivenAlbum(PermissionType permission,
		Integer albumId) {

		log.info("Starting to get a list of users with permission [{}] for the album with id [{}]",
			permission, albumId);

		return userAppMapper.domainListToDtoList(
			adminService.findUsersByPermissionForAGivenAlbum(permission, albumId));
	}

}
