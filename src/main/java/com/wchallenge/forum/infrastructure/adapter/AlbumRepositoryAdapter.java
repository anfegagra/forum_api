package com.wchallenge.forum.infrastructure.adapter;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.port.AlbumRepositoryPort;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.AlbumJpaRepository;
import com.wchallenge.forum.infrastructure.adapter.repository.mapper.AlbumRepositoryMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AlbumRepositoryAdapter implements AlbumRepositoryPort {

	private final AlbumJpaRepository albumJpaRepository;
	private final AlbumRepositoryMapper albumRepositoryMapper;

	public AlbumRepositoryAdapter(
		AlbumJpaRepository albumJpaRepository,
		AlbumRepositoryMapper albumRepositoryMapper) {
		this.albumJpaRepository = albumJpaRepository;
		this.albumRepositoryMapper = albumRepositoryMapper;
	}

	@Override
	public List<Album> findByUserId(int userId) {

		return albumRepositoryMapper
			.domainListToEntityList(albumJpaRepository.findByUserId(userId));
	}
}
