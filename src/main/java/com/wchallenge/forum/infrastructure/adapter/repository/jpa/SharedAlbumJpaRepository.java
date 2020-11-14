package com.wchallenge.forum.infrastructure.adapter.repository.jpa;

import com.wchallenge.forum.infrastructure.adapter.repository.entity.SharedAlbumEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedAlbumJpaRepository extends JpaRepository<SharedAlbumEntity, Long> {

	@Query("select sa from SharedAlbumEntity sa where sa.userId = :userId and sa.album.id = :albumId and sa.permission.id = :permissionId")
	SharedAlbumEntity findByUserIdAndAlbumIdAndPermissionId(
		@Param("userId") long userId,
		@Param("albumId") long albumId,
		@Param("permissionId") long permissionId
	);

	@Query("select userId from SharedAlbumEntity sa where sa.permission.id = :permissionId and sa.album.id = :albumId")
	List<Long> findByPermissionIdAndAlbumId(
		@Param("permissionId") long permissionId,
		@Param("albumId") long albumId
	);
}
