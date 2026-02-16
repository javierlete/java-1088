package com.ipartek.formacion.ejemplos.ipartube.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class JaxRsApplication extends ResourceConfig {
	public JaxRsApplication() {
		packages("com.ipartek.formacion.ejemplos.ipartube.rest");
		register(new AuthDynamicFeature(new JwtAuthFilter(new JwtAuthenticator())));
		register(RolesAllowedDynamicFeature.class);
		register(new AuthValueFactoryProvider.Binder<>(Usuario.class));
	}
}