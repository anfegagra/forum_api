package com.wchallenge.forum.infrastructure.controller;

import com.wchallenge.forum.domain.exception.ForumNotificationCode;
import com.wchallenge.forum.infrastructure.adapter.UserDetailsServiceAdapter;
import com.wchallenge.forum.infrastructure.config.security.JWTUtil;
import com.wchallenge.forum.infrastructure.controller.dto.ApiResponseForumDto;
import com.wchallenge.forum.infrastructure.controller.dto.AuthRequestDto;
import com.wchallenge.forum.infrastructure.controller.dto.AuthResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager; // gestor de autenticación de spring
	private final UserDetailsServiceAdapter userDetailsServiceAdapter;
	private final JWTUtil jwtUtil;

	public AuthController(
		AuthenticationManager authenticationManager,
		UserDetailsServiceAdapter userDetailsServiceAdapter,
		JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.userDetailsServiceAdapter = userDetailsServiceAdapter;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/authenticate")
	public ApiResponseForumDto<AuthResponseDto> createToken(
		@RequestBody AuthRequestDto request) {

		try {
			authenticationManager.authenticate( // verificar si el usuario y contraseña son correctos
				new UsernamePasswordAuthenticationToken(request.getUsername(),
					request.getPassword()));

			UserDetails userDetails = userDetailsServiceAdapter
				.loadUserByUsername(request.getUsername());

			String jwt = jwtUtil.generateToken(userDetails);

			return new ApiResponseForumDto<>(new AuthResponseDto(jwt), null);

		} catch (BadCredentialsException e) {

			throw new com.wchallenge.forum.domain.exception.BadCredentialsException(
				ForumNotificationCode.BAD_CREDENTIALS);
		}

	}
}
