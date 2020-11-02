package com.wchallenge.forum.application.service;

import com.wchallenge.forum.application.dto.album.SharedAlbumRequestDto;
import com.wchallenge.forum.application.mapper.AdminAppMapper;
import com.wchallenge.forum.domain.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminAppService {

	private final AdminService adminService;
	private final AdminAppMapper adminAppMapper;

	public AdminAppService(AdminService adminService, AdminAppMapper adminAppMapper) {
		this.adminService = adminService;
		this.adminAppMapper = adminAppMapper;
	}

	public boolean registerSharedAlbum(SharedAlbumRequestDto sharedAlbumRequestDto) {

		log.info(
			"Starting to register a shared album with album id {} and user id {} with permission {}",
			sharedAlbumRequestDto.getAlbumId(), sharedAlbumRequestDto.getUserId(),
			sharedAlbumRequestDto.getPermission());

		return adminService.registerSharedAlbum(adminAppMapper.dtoToDomain(sharedAlbumRequestDto));
	}

}
