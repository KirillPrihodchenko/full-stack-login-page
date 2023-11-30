package com.auth.fullstackloginpage.auth.util;

import com.auth.fullstackloginpage.exception.InvalidJWTAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${spring.jwt.secret}")
    private String secret;
    @Value("${spring.jwt.secret}")
    private String expiredTime;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetailsImp.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiredTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsername(String token) {

        return getAllClaimsFromToken(token)
                .getSubject();
    }

    public boolean validateToken(String token) throws InvalidJWTAuthenticationException {

        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);

            return true;
        }
        catch (JwtException | IllegalArgumentException e) {

            throw new InvalidJWTAuthenticationException("Expired or invalid JWT token", e);
        }
    }

    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}