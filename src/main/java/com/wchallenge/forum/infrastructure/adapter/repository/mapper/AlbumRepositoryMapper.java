package com.wchallenge.forum.infrastructure.adapter.repository.mapper;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.AlbumEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AlbumRepositoryMapper {

	AlbumEntity domainToEntity(Album album);

	List<Album> domainListToEntityList(List<AlbumEntity> albums);

	@Mapping(target = "userId", source = "user.id")
	Album domainToEntity(AlbumEntity album);
}
