package com.wchallenge.forum.infrastructure.config.security.filter;

import com.wchallenge.forum.infrastructure.adapter.UserDetailsServiceAdapter;
import com.wchallenge.forum.infrastructure.config.security.JWTUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilterRequest extends OncePerRequestFilter { // se ejecuta cada que hay una petición

	private final JWTUtil jwtUtil;
	private final UserDetailsServiceAdapter userDetailsServiceAdapter;

	public JwtFilterRequest(JWTUtil jwtUtil,
		UserDetailsServiceAdapter userDetailsServiceAdapter) {
		this.jwtUtil = jwtUtil;
		this.userDetailsServiceAdapter = userDetailsServiceAdapter;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			String jwt = authorizationHeader.substring(7);

			String username = jwtUtil.extractUsername(jwt);

			if (username != null
				&& SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = userDetailsServiceAdapter.loadUserByUsername(username); // verificar si el usuario existe dentro del sistema de autenticación

				if (jwtUtil.validateToken(jwt, userDetails)) {

					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( // levantar una sesión para ese usuario
						userDetails, null, userDetails.getAuthorities());

					authToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // añadir los detalles de la conexión para evaluar el navegador, horario, so, ...

					SecurityContextHolder.getContext().setAuthentication(authToken); // para que quede en el contexto y no tenga que pasar siempre por las mismas validaciones
				}
			}
		}

		filterChain.doFilter(request, response);
	}
}
