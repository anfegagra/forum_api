package com.wchallenge.forum.infrastructure.adapter.repository.jpa;

import com.wchallenge.forum.infrastructure.adapter.repository.entity.AlbumEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumJpaRepository extends JpaRepository<AlbumEntity, Long> {

	@Query("select ae from AlbumEntity ae where ae.user.id = :userId")
	List<AlbumEntity> findByUserId(@Param("userId") long userId);
}
