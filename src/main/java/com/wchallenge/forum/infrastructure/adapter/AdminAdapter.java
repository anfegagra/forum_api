package com.wchallenge.forum.infrastructure.adapter;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.PermissionType;
import com.wchallenge.forum.domain.model.user.RoleType;
import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.domain.port.AdminPort;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.AlbumEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.SharedAlbumEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.UserEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.AlbumJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.PermissionJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.SharedAlbumJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.UserJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.mapper.AlbumRepositoryMapper;
import com.wchallenge.forum.infrastructure.adapter.repository.mapper.UserRepositoryMapper;
import java.util.List;
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
	private final SharedAlbumJpaRepository sharedAlbumJpaRepository;

	public AdminAdapter(
		UserJpaRepository userJpaRepository,
		UserRepositoryMapper userRepositoryMapper,
		AlbumJpaRepository albumJpaRepository,
		AlbumRepositoryMapper albumRepositoryMapper,
		PermissionJpaRepository permissionJpaRepository,
		BCryptPasswordEncoder bCryptPasswordEncoder,
		SharedAlbumJpaRepository sharedAlbumJpaRepository) {
		this.userJpaRepository = userJpaRepository;
		this.userRepositoryMapper = userRepositoryMapper;
		this.albumJpaRepository = albumJpaRepository;
		this.albumRepositoryMapper = albumRepositoryMapper;
		this.permissionJpaRepository = permissionJpaRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.sharedAlbumJpaRepository = sharedAlbumJpaRepository;
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
		albumJpaRepository.save(albumEntity);
		log.info("The shared album with id {} was registered successfully", album.getId());

		SharedAlbumEntity sharedAlbumEntity = new SharedAlbumEntity();
		sharedAlbumEntity.setUserId(userEntity.getId());
		sharedAlbumEntity.setAlbum(albumEntity);
		sharedAlbumEntity.setPermission(permissionJpaRepository.findByType(permission));

		if (!isSharedAlbumAlreadyRegistered(sharedAlbumEntity)) {
			sharedAlbumJpaRepository.save(sharedAlbumEntity);
		}

		return true;
	}

	private boolean isSharedAlbumAlreadyRegistered(SharedAlbumEntity sharedAlbumEntity) {

		return sharedAlbumJpaRepository
			.findByUserIdAndAlbumIdAndPermissionId(sharedAlbumEntity.getUserId(),
				sharedAlbumEntity.getAlbum().getId(), sharedAlbumEntity.getPermission().getId())
			!= null;
	}

	@Override
	public List<Long> findUsersByPermissionForAGivenAlbum(PermissionType permission, int albumId) {

		return sharedAlbumJpaRepository
			.findByPermissionIdAndAlbumId(permission.getPermissionId(), albumId);
	}
}
