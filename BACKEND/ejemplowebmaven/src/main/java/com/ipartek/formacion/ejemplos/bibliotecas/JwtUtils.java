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

	public static String generate(String username, String role) {
		try {
			JWTClaimsSet claims = new JWTClaimsSet.Builder().subject(username).claim("role", role)
					.expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS))).build();

			SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);

			Properties p = new Properties();
			p.load(JwtUtils.class.getResourceAsStream("/jwt.properties"));
			byte[] secret = p.getProperty("jwt.secret").getBytes();
			
			jwt.sign(new MACSigner(secret));
			return jwt.serialize();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
