package com.cibertec.market.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constants {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    // JWT
    public static final String SUPER_SECRET_TEXT = "KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp";

    public static final long TOKEN_VALIDITY_SECONDS = 600;

    public static Key getSigningKeyB64(String secret) {
        byte[] keyBites = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBites);
    }

    public static Key getSigningKey(String secret) {
        byte[] keyBites = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBites);
    }
}
