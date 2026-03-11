package com.ipartek.formacion.ejemplos.ipartexspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
	// AUTENTICACIÓN
	
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails javier = User.withUsername("javier@email.net").password("javier").roles("ADMINISTRADOR").build();
		UserDetails pepe = User.withUsername("pepe@email.net").password("pepe").roles("USUARIO").build();
		
		return new InMemoryUserDetailsManager(javier, pepe);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}