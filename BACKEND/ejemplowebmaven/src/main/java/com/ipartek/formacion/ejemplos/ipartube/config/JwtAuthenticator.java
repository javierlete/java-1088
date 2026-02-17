package com.ipartek.formacion.ejemplos.ipartube.config;

import java.util.Optional;
import java.util.Properties;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;

import io.dropwizard.auth.Authenticator;

public class JwtAuthenticator implements Authenticator<String, Usuario> {

	// Autenticación de las cabeceras de REST recibidas por cada petición
	// Authentication: Bearer token
    @Override
    public Optional<Usuario> authenticate(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);

            // Leemos el fichero jwt.properties en el raíz de paquetes
            Properties p = new Properties();
			p.load(JwtAuthenticator.class.getResourceAsStream("/jwt.properties"));
			
			// Sacamos la clave guardada en jwt.secret
			byte[] secret = p.getProperty("jwt.secret").getBytes();
            
			// Verificamos que la clave concuerda con la recibida en el JSON Web Token (JWT)
            if (!jwt.verify(new MACVerifier(secret))) {
                // Si no cuadra el token con la firma, no estás autenticado
            	return Optional.empty();
            }

            // La firma del token es de nuestro servidor por lo que nos fiamos de los datos recibidos
            // Extraemos los "claims" del token
            // Subject
            String username = jwt.getJWTClaimsSet().getSubject();
            // Rol
            String role = jwt.getJWTClaimsSet().getStringClaim("role");
            // Id
            Long id = jwt.getJWTClaimsSet().getLongClaim("id");

            // Construimos un objeto para dar el resultado unificado de toda la información recibida
            return Optional.of(new Usuario(id, null, username, null, null, new Rol(null, role, null)));

        } catch (Exception e) {
        	// Ante cualquier error, devolvemos que no está autenticado
            return Optional.empty();
        }
    }
}
