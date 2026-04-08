package com.ipartek.formacion.ejemplos.iparfood.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ipartek.formacion.ejemplos.iparfood.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
	private final UsuarioService usuarioService;
	private final LoginSuccessHandler loginSuccessHandler;

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
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(requests -> requests
				.requestMatchers("/js/**", "/css/**", "/imgs/**", "/api/**").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMINISTRADOR")
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.successHandler(loginSuccessHandler)
				.permitAll()
			)
			.logout(LogoutConfigurer::permitAll);
		// @formatter:on

		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedOriginPattern("*");
	    config.addAllowedMethod("*");
	    config.addAllowedHeader("*");
	    config.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);

	    return source;
	}

}