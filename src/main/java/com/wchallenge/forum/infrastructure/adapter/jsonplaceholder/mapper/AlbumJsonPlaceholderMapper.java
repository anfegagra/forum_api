package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.mapper;

import com.wchallenge.forum.domain.model.album.Photo;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.album.PhotoResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AlbumJsonPlaceholderMapper {

	List<Photo> responsePhotosListToDomainPhotosList(List<PhotoResponse> photoResponseList);
}