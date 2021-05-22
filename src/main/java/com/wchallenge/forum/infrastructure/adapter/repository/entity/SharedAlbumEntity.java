package com.wchallenge.forum.infrastructure.adapter.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shared_album")
@NoArgsConstructor
@Getter
@Setter
public class SharedAlbumEntity {

	@EmbeddedId
	private SharedAlbumPk id = new SharedAlbumPk();

	@Column(name = "user_id")
	private Long userId;

	@ManyToOne(cascade = CascadeType.PERSIST)
//	@MapsId("album_id")
	@JoinColumn(name = "album_id", insertable = false, updatable = false)
//	@JsonIgnore
	private AlbumEntity album;

	@ManyToOne(cascade = CascadeType.PERSIST)
//	@MapsId("permission_id")
	@JoinColumn(name = "permission_id", insertable = false, updatable = false)
//	@JsonIgnore
	private PermissionEntity permission;
}
