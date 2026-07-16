package com.vaishnavi.nexora.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET =
            "mySecretKeyForNexoraProjectPlacement2026mySecretKey123456";

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    private final MacAlgorithm algorithm = Jwts.SIG.HS256;

    // Generate JWT Token
    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key, algorithm)
                .compact();
    }

    // Extract Email
    public String extractEmail(String token) {

        return getClaims(token).getSubject();
    }

    // Check Expiry
    public boolean isTokenExpired(String token) {

        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // Validate Token
    public boolean isTokenValid(String token, String email) {

        return extractEmail(token).equals(email)
                && !isTokenExpired(token);
    }

    // Read Claims
    private Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}