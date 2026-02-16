package com.ipartek.formacion.ejemplos.ipartube.config;

import java.util.Optional;
import java.util.Properties;

import com.ipartek.formacion.ejemplos.bibliotecas.JwtUtils;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;

import io.dropwizard.auth.Authenticator;

public class JwtAuthenticator implements Authenticator<String, Usuario> {

    @Override
    public Optional<Usuario> authenticate(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);

            Properties p = new Properties();
			p.load(JwtUtils.class.getResourceAsStream("/jwt.properties"));
			byte[] secret = p.getProperty("jwt.secret").getBytes();
            
            if (!jwt.verify(new MACVerifier(secret))) {
                return Optional.empty();
            }

            String username = jwt.getJWTClaimsSet().getSubject();
            String role = jwt.getJWTClaimsSet().getStringClaim("role");

            return Optional.of(new Usuario(null, null, username, null, null, new Rol(null, role, null)));

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
