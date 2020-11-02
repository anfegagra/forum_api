package com.wchallenge.forum.domain.model.album;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionType {

	READ(1),
	WRITE(2);

	private final int permissionId;
}
