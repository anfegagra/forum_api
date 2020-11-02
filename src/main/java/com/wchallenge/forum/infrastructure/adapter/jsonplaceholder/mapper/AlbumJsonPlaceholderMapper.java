package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.mapper;

import com.wchallenge.forum.domain.model.album.Album;
import com.wchallenge.forum.domain.model.album.Photo;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album.AlbumResponse;
import com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album.PhotoResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AlbumJsonPlaceholderMapper {

	List<Photo> responsePhotosListToDomainPhotosList(List<PhotoResponse> photoResponseList);

	List<Album> responseAlbumsListToDomainAlbumsList(List<AlbumResponse> albumResponseList);

	Album responseAlbumToDomainAlbum(AlbumResponse albumResponse);
}
