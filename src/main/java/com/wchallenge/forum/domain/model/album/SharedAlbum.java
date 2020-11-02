package com.wchallenge.forum.domain.model.album;

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
public class SharedAlbum {

	private int albumId;
	private int userId;
	private PermissionType permission;
}
