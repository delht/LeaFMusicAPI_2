package de.lht.leafmusic3.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.expiration.access-token}")
    private long ACCESS_EXPIRATION;
    @Value("${jwt.expiration.refresh-token}")
    private long REFRESH_EXPIRATION;

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username, String idUser, String email) {
        return generateAccessToken(username, idUser, email);
    }

    public String generateAccessToken(String username, String idUser, String email) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + ACCESS_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .claim("idUser", idUser)
                .claim("email", email)
                .claim("tokenType", "access")
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String username, String idUser, String email) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + REFRESH_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .claim("idUser", idUser)
                .claim("email", email)
                .claim("tokenType", "refresh")
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
        } catch (JwtException e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
        return false;
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getTokenType(String token) {
        try {
            Claims claims = extractAllClaims(token);
            Object t = claims.get("tokenType");
            return t != null ? t.toString() : null;
        } catch (JwtException e) {
            return null;
        }
    }
}
