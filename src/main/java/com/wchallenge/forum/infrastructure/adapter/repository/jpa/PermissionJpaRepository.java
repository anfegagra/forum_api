package com.wchallenge.forum.infrastructure.adapter.repository.jpa;

import com.wchallenge.forum.domain.model.album.PermissionType;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionJpaRepository extends JpaRepository<PermissionEntity, Long> {

	PermissionEntity findByType(PermissionType permissionType);
}
