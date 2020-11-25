package com.wchallenge.forum.infrastructure.config.security;

import com.wchallenge.forum.infrastructure.adapter.UserDetailsServiceAdapter;
import com.wchallenge.forum.infrastructure.config.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceAdapter userDetailsServiceAdapter;

	@Autowired
	private JwtFilterRequest jwtFilterRequest;

	// configurar el manejador de autenticación para que no la configure por defecto y se usen las credenciales almacenadas en bd
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceAdapter);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// deshabilitar peticiones cruzadas
		http.csrf().disable().authorizeRequests()
			.antMatchers("/**/authenticate").permitAll()
			.antMatchers("/**/h2-console/**").permitAll()
			.anyRequest().authenticated()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // indicar que la sesión de nuestra aplicación será sin estado ya que los jwt son los que
		// van a controlar cada petición

		http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class); // utilizar el filtro y decirle qué tipo de filtro es

		http.headers().frameOptions().disable();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean(); // decirle a spring que siga controlando la gestión de autenticación
	}
}
