package com.wchallenge.forum.infrastructure.adapter.repository.mapper;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.AlbumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AlbumRepositoryMapper {

	AlbumEntity domainToEntity(Album album);
}
