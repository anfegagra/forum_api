package com.wchallenge.forum.infrastructure.adapter.repository.entity;

import com.wchallenge.forum.domain.model.user.RoleType;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@Column(name = "user_id")
	private Long id;

	private String username;

	private String password;

	@Enumerated(EnumType.STRING)
	private RoleType role;

	@OneToMany(mappedBy = "user")
	private Set<AlbumEntity> albums;
}
