package com.wchallenge.forum.infrastructure.adapter.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "album")
public class AlbumEntity {

	@Id
	@Column(name = "album_id")
	private Long id;

	private String title;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

//	@ManyToMany(cascade = CascadeType.PERSIST)
//	@JoinTable(
//		name = "shared_album",
//		joinColumns = {
//			@JoinColumn(name = "album_id", nullable = false, updatable = false)
//		},
//		inverseJoinColumns = {
//			@JoinColumn(name = "permission_id", nullable = false, updatable = false)
//		}
//	)
//	private Set<PermissionEntity> permissions;

	@OneToMany(mappedBy = "album")
//	@JsonIgnore
	private List<SharedAlbumEntity> sharedAlbums = new ArrayList<>();


}
