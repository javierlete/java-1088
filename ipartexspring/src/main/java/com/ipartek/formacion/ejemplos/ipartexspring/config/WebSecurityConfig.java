package com.ipartek.formacion.ejemplos.ipartexspring.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ipartek.formacion.ejemplos.ipartexspring.servicios.UsuarioService;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
	@Autowired
	private UsuarioService usuarioService;

	// AUTENTICACIÓN
	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {
		return (username -> UsuarioLogin.of(
				usuarioService.buscarPorEmail(username).orElseThrow(() -> new UsernameNotFoundException(username))));
	}

	@SuppressWarnings("deprecation")
	@Bean
	PasswordEncoder passwordEncoder() {
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();

		encoders.put("bcrypt", new BCryptPasswordEncoder(14));
		encoders.put("noop", NoOpPasswordEncoder.getInstance());

		return new DelegatingPasswordEncoder(idForEncode, encoders);
	}

	// AUTORIZACIÓN
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		// @formatter:off
		http
			.authorizeHttpRequests(requests -> requests
				.requestMatchers("/usuario/**").authenticated()
				.anyRequest().permitAll()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout(LogoutConfigurer::permitAll);
		// @formatter:on

		return http.build();
	}
}