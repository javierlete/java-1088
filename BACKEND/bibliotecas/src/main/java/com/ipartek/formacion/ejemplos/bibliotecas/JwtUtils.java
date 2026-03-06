package com.ipartek.formacion.ejemplos.bibliotecas;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class JwtUtils {

	public static String generate(Long id, String username, String role) {
		try {
			// Creamos un conjunto de "claims" que contiene el id y nombre de usuario y el rol con caducidad de una hora
			JWTClaimsSet claims = new JWTClaimsSet.Builder().subject(username).claim("role", role).claim("id", id)
					.expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS))).build();

			// Creamos un token firmado con los "claims" que habíamos creado previamente
			SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);

			// Leemos la clave para firmar el token del fichero de configuración
			Properties p = new Properties();
			p.load(JwtUtils.class.getResourceAsStream("/jwt.properties"));
			byte[] secret = p.getProperty("jwt.secret").getBytes();
			
			// Firmamos con la clave recibida
			jwt.sign(new MACSigner(secret));
			
			// Devolvemos el token en formato texto
			return jwt.serialize();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
