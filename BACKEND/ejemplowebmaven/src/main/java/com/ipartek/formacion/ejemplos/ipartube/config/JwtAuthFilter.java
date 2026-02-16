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
        String header = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            abort(ctx, Response.Status.UNAUTHORIZED);
            return;
        }

        String token = header.substring("Bearer ".length());

        try {
            Optional<Usuario> user = authenticator.authenticate(token);

            if (user.isEmpty()) {
                abort(ctx, Response.Status.UNAUTHORIZED);
                return;
            }

            ctx.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return user.get();
                }

                @Override
                public boolean isUserInRole(String role) {
                    return role.equals(user.get().getRol().getNombre());
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
            abort(ctx, Response.Status.UNAUTHORIZED);
        }
    }

    private void abort(ContainerRequestContext ctx, Response.Status status) {
        ctx.abortWith(Response.status(status).build());
    }
}
