package com.wchallenge.forum.infrastructure.adapter.repository.jpa;

import com.wchallenge.forum.infrastructure.adapter.repository.entity.SharedAlbumEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.SharedAlbumPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedAlbumJpaRepository extends JpaRepository<SharedAlbumEntity, SharedAlbumPk> {

	@Query("select sa from SharedAlbumEntity sa where sa.permission.id = :permissionId")
	SharedAlbumEntity findByPermissionId(@Param("permissionId") Long permissionId);
}
