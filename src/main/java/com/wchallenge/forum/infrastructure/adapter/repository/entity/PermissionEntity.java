package com.wchallenge.forum.infrastructure.adapter.repository.entity;

import com.wchallenge.forum.domain.model.album.PermissionType;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class PermissionEntity {

	@Id
	@Column(name = "permission_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private PermissionType type;

//	@ManyToMany(mappedBy = "permissions")
//	private Set<AlbumEntity> albums;

	@OneToMany(mappedBy = "album")
	private List<SharedAlbumEntity> permission;

}
