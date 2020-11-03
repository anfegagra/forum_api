package com.wchallenge.forum.infrastructure.adapter;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.PermissionType;
import com.wchallenge.forum.domain.model.user.RoleType;
import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.domain.port.AdminPort;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.AlbumEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.UserEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.AlbumJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.PermissionJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.UserJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.mapper.AlbumRepositoryMapper;
import com.wchallenge.forum.infrastructure.adapter.repository.mapper.UserRepositoryMapper;
import java.util.Collections;
import java.util.HashSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminAdapter implements AdminPort {

	private final UserJpaRepository userJpaRepository;
	private final UserRepositoryMapper userRepositoryMapper;
	private final AlbumJpaRepository albumJpaRepository;
	private final AlbumRepositoryMapper albumRepositoryMapper;
	private final PermissionJpaRepository permissionJpaRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public AdminAdapter(
		UserJpaRepository userJpaRepository,
		UserRepositoryMapper userRepositoryMapper,
		AlbumJpaRepository albumJpaRepository,
		AlbumRepositoryMapper albumRepositoryMapper,
		PermissionJpaRepository permissionJpaRepository,
		BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userJpaRepository = userJpaRepository;
		this.userRepositoryMapper = userRepositoryMapper;
		this.albumJpaRepository = albumJpaRepository;
		this.albumRepositoryMapper = albumRepositoryMapper;
		this.permissionJpaRepository = permissionJpaRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public boolean registerSharedAlbum(User user, Album album, PermissionType permission) {

		UserEntity userEntity = userRepositoryMapper.domainToEntity(user);
		userEntity.setPassword(bCryptPasswordEncoder.encode(user.getUsername()));
		userEntity.setRole(RoleType.ROLE_USER);
		userJpaRepository.save(userEntity);
		log.info("The user with id {} was registered successfully", user.getId());

		AlbumEntity albumEntity = albumRepositoryMapper.domainToEntity(album);
		albumEntity.setUser(userEntity);
		albumEntity.setPermissions(
			new HashSet<>(Collections.singleton(permissionJpaRepository.findByType(permission))));
		albumJpaRepository.save(albumEntity);
		log.info("The shared album with id {} was registered successfully", album.getId());

		return true;
	}
}
