package com.cibertec.market.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;

import static com.cibertec.market.security.Constants.*;

@Configuration
public class JWTAuthenticationConfig {

    public String getJWTToken(String username, String nombres, String apellidos) {
        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN");

        String token = Jwts.builder()
                .setId("Rafael")
                .setSubject(username)
                .claim("nombre", nombres)
                .claim("apellido", apellidos)
                .claim("authorities", grantedAuthorityList.stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(getSigningKey(SUPER_SECRET_TEXT), SignatureAlgorithm.HS256).compact();

        return token;
    }

    public Claims decodeToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey(SUPER_SECRET_TEXT))
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }
}
