package com.wchallenge.forum.infrastructure.adapter;

import com.wchallenge.forum.domain.exception.ForumNotificationCode;
import com.wchallenge.forum.domain.exception.UsernameNotFoundException;
import com.wchallenge.forum.infrastructure.adapter.repository.entity.UserEntity;
import com.wchallenge.forum.infrastructure.adapter.repository.jpa.UserJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDetailsServiceAdapter implements UserDetailsService {

	private final UserJpaRepository userJpaRepository;

	public UserDetailsServiceAdapter(
		UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {

		log.info("Searching user in forumdb with username: {}", username);

		UserEntity user = Optional.ofNullable(userJpaRepository.findByUsername(username))
			.orElseThrow(
				() -> new UsernameNotFoundException(ForumNotificationCode.USERNAME_NOT_FOUND));

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(user.getRole().name()));

		return new User(user.getUsername(), user.getPassword(), roles);
	}
}
