package com.wchallenge.forum.infrastructure.adapter;

import com.wchallenge.forum.domain.exception.DataNotFoundException;
import com.wchallenge.forum.domain.exception.ForumNotificationCode;
import com.wchallenge.forum.domain.exception.UsernameNotFoundException;
import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.PermissionType;
import com.wchallenge.forum.domain.model.album.SharedAlbum;
import com.wchallenge.forum.domain.model.user.RoleType;
import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.domain.port.AdminPort;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.AlbumEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.SharedAlbumEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.SharedAlbumPk;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.UserEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.AlbumJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.PermissionJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.SharedAlbumJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.UserJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.mapper.AlbumRepositoryMapper;
import com.wchallenge.forum.infrastructure.adapter.repository.mapper.UserRepositoryMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
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
//		albumEntity.setPermissions(
//			new HashSet<>(Collections.singleton(permissionJpaRepository.findByType(permission))));
//		albumEntity.setSharedAlbums(new ArrayList<>());
//		albumEntity.getSharedAlbums()
//			.add(sharedAlbumJpaRepository.findByPermissionId((long) permission.getPermissionId()));

		SharedAlbumEntity sharedAlbumEntity = new SharedAlbumEntity();
//		sharedAlbumEntity.setAlbum(albumEntity);
//		sharedAlbumEntity.setPermission(permissionJpaRepository.findByType(permission));
		sharedAlbumEntity.setUserId((long) user.getId());
//		sharedAlbumJpaRepository.save(sharedAlbumEntity);

//		albumEntity.setSharedAlbums(Collections.singletonList(sharedAlbumEntity));
		albumJpaRepository.saveAndFlush(albumEntity);
		log.info("The shared album with id {} was registered successfully", album.getId());

//		SharedAlbumPk sharedAlbumPk = new SharedAlbumPk();
//		sharedAlbumPk.setAlbumId((long) album.getId());
//		sharedAlbumPk.setPermissionId((long) permission.getPermissionId());

		return true;
	}

	@Override
	public boolean updateUserPermissionInASharedAlbum(SharedAlbum sharedAlbum) {

		Optional<UserEntity> userEntity = userJpaRepository
			.findById((long) sharedAlbum.getUserId());
		if (!userEntity.isPresent()) {
			throw new UsernameNotFoundException(ForumNotificationCode.USERNAME_NOT_FOUND);
		}

		Optional<AlbumEntity> albumEntity = albumJpaRepository
			.findById((long) sharedAlbum.getAlbumId());
		if (!albumEntity.isPresent()) {
			throw new DataNotFoundException(ForumNotificationCode.ALBUM_NOT_FOUND);
		}

//		albumEntity.get().setPermissions(new HashSet<>(Collections
//			.singleton(permissionJpaRepository.findByType(sharedAlbum.getPermission()))));
		log.info("The permission {} was updated to the user {} for the album with id {}",
			sharedAlbum.getPermission(), userEntity.get().getUsername(), sharedAlbum.getAlbumId());

		return true;
	}

}
