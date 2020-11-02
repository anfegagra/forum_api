package com.wchallenge.forum.infrastructure.adapter.repository.jpa;

import com.wchallenge.forum.infrastructure.adapter.repository.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumJpaRepository extends JpaRepository<AlbumEntity, Long> {

}
