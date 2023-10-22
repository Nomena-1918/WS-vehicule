package org.vehicule.wsvehicule.util;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TokenGenerator {

    @Value("${custom.token.key}")
    private static String tokenKey;

    public static String getJWTToken(String username) throws NoSuchAlgorithmException, InvalidKeyException {
        String secretKey = tokenKey+System.currentTimeMillis();

        // Créer une instance de HMAC avec l'algorithme SHA-256
        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        hmac.init(secretKeySpec);

        // Calculer le HMAC du nom d'utilisateur
        byte[] macBytes = hmac.doFinal(username.getBytes());

        // Convertir le résultat en une représentation en base64
        return Base64.getEncoder().encodeToString(macBytes);
    }
}
