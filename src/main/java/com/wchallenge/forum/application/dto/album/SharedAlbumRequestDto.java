package com.wchallenge.forum.application.dto.album;

import com.wchallenge.forum.domain.model.album.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SharedAlbumRequestDto {

	private int albumId;
	private int userId;
	private PermissionType permission;
}
