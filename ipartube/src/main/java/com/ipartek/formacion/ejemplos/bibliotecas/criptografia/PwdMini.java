package com.ipartek.formacion.ejemplos.bibliotecas.criptografia;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PwdMini {

    private static final int LONGITUD = 256;
	private static final int ITERACIONES = 150000;

	public static String hash(String p) throws Exception {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        byte[] h = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(new PBEKeySpec(p.toCharArray(), salt, ITERACIONES, LONGITUD))
                .getEncoded();

        return Base64.getEncoder().encodeToString(salt) + ":" +
               Base64.getEncoder().encodeToString(h);
    }

    public static boolean verify(String p, String stored) throws Exception {
        String[] s = stored.split(":");
        byte[] salt = Base64.getDecoder().decode(s[0]);
        byte[] expected = Base64.getDecoder().decode(s[1]);

        byte[] h = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(new PBEKeySpec(p.toCharArray(), salt, ITERACIONES, expected.length * 8))
                .getEncoded();

        int diff = 0;
        for (int i = 0; i < h.length; i++) diff |= h[i] ^ expected[i];
        return diff == 0;
    }
}
