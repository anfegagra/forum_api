package com.wchallenge.forum.infrastructure.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

	// 512 bits
	private static final String KEY = "G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRfUjXn2r5u8x/A";

	public String generateToken(UserDetails userDetails) {

		return Jwts.builder()
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 300))
			.claim("roles", userDetails.getAuthorities())
			.signWith(SignatureAlgorithm.HS256, KEY).compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) { // verificar que el token fue creado para el usuario que está haciendo la petición y que no haya expirado
		return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
	}

	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	public boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}

	private Claims getClaims(String token) { // verifica que la firma sea correcta y se sacan los claims
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}
}
