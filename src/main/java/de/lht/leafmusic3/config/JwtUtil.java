package de.lht.leafmusic3.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Inject giá trị từ application.yaml vào biến SECRET
    // @Value("${jwt.secret}")
    private String SECRET = "7a2f61c3a2e89e7a6c3b2c3f52d3e5d1bbf86dbd59715ab3e12e572fb18f3c35";

    // Thời gian hết hạn của token (có thể cấu hình trong application.yaml)
    // @Value("${jwt.expiration}")
    // default expirations in seconds
    private long ACCESS_EXPIRATION = 15 * 60; // 15 minutes
    private long REFRESH_EXPIRATION = 7 * 24 * 3600; // 7 days

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username, String idUser, String email) {
        // Legacy: generate access token with default access expiration
        return generateAccessToken(username, idUser, email);
    }

    public String generateAccessToken(String username, String idUser, String email) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + ACCESS_EXPIRATION * 1000);

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
        Date expirationTime = new Date(now.getTime() + REFRESH_EXPIRATION * 1000);

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
