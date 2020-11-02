package com.wchallenge.forum.application.mapper;

import com.wchallenge.forum.application.dto.album.SharedAlbumRequestDto;
import com.wchallenge.forum.domain.model.album.SharedAlbum;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdminAppMapper {

	SharedAlbum dtoToDomain(SharedAlbumRequestDto sharedAlbumRequestDto);
}
