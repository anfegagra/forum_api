package com.wchallenge.forum.infrastructure.config;

import com.wchallenge.forum.domain.model.user.RoleType;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.UserEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.UserJpaRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextEventListener {

	private final UserJpaRepository userJpaRepository;

	public ContextEventListener(
		UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@EventListener
	private void saveAdminOnStartUp(ContextRefreshedEvent event) {

		UserEntity userEntity = UserEntity.builder().id(0L).username("admin")
			.password(("$2a$10$a1nGepc6uX6sJVFD6EsAWeM7cN97NFsagDL6.NNNXr7LrADOMs/HC"))
			.role(RoleType.ROLE_ADMIN).build();

		userJpaRepository.save(userEntity);
	}
}
