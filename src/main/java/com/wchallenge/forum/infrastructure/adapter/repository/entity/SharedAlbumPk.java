package com.wchallenge.forum.infrastructure.adapter.repository.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SharedAlbumPk implements Serializable {

	@Column(name = "album_id")
	private Long albumId;

	@Column(name = "permission_id")
	private Long permissionId;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SharedAlbumPk that = (SharedAlbumPk) o;
		return albumId.equals(that.albumId) &&
			permissionId.equals(that.permissionId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(albumId, permissionId);
	}
}
