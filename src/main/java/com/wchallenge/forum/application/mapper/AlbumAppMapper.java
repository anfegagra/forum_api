package com.wchallenge.forum.application.mapper;

import com.wchallenge.forum.application.dto.album.PhotoDto;
import com.wchallenge.forum.domain.model.album.Photo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AlbumAppMapper {

	List<PhotoDto> domainPhotosListToDtoPhotosList(List<Photo> photo);
}
