package com.ipartek.formacion.ejemplos.ipartube.config;

import java.security.Principal;
import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

import io.dropwizard.auth.Authenticator;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthFilter implements ContainerRequestFilter {

    private final Authenticator<String, Usuario> authenticator;

    public JwtAuthFilter(Authenticator<String, Usuario> authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public void filter(ContainerRequestContext ctx) {
    	// Recogemos el texto de la cabecera de la petición recibida con la clave "Authorization"
    	
    	// El contenido de header será "Bearer TOKEN" donde TOKEN será la información que habremos enviado en login 
        String header = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Comprobamos si existe la cabecera y comienza con "Bearer "
        if (header == null || !header.startsWith("Bearer ")) {
        	// Si no viene la cabecera o no comienza con Bearer, no permitimos la operación
            noAutorizado(ctx);
            return;
        }

        // Si existe, extraemos el token de la cadena
        String token = header.substring("Bearer ".length());

        try {
        	// Autenticamos el usuario a través del JwtAuthenticator
            Optional<Usuario> usuario = authenticator.authenticate(token);

            // Si no está autenticado
            if (usuario.isEmpty()) {
                noAutorizado(ctx);
                return;
            }

            // En el caso de que todo sea correcto, se crea un contexto de seguridad
            ctx.setSecurityContext(new SecurityContext() {
            	// Usuario autenticado
                @Override
                public Principal getUserPrincipal() {
                    return usuario.get();
                }

                // Método para comprobar si el usuario tiene un rol determinado
                @Override
                public boolean isUserInRole(String role) {
                    return role.equals(usuario.get().getRol().getNombre());
                }

                @Override
                public boolean isSecure() {
                    return false;
                }

                @Override
                public String getAuthenticationScheme() {
                    return "Bearer";
                }
            });

        } catch (Exception e) {
            noAutorizado(ctx);
        }
    }

    private void noAutorizado(ContainerRequestContext ctx) {
        ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
}
