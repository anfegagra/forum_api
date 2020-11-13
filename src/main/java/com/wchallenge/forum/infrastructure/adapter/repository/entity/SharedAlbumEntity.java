package com.wchallenge.forum.infrastructure.adapter.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shared_album")
public class SharedAlbumEntity {

	@Id
	@Column(name = "shared_album_id")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "label_sequence")
//	@SequenceGenerator(name = "label_sequence", sequenceName = "label_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_id")
	private long userId;

	@ManyToOne
	@JoinColumn(name = "album_id")
	private AlbumEntity album;

	@ManyToOne
	@JoinColumn(name = "permission_id")
	private PermissionEntity permission;
}
