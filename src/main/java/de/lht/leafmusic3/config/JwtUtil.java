package de.lht.leafmusic3.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Inject giá trị từ application.properties vào biến SECRET
    // @Value("${jwt.secret}")
    private String SECRET = "7a2f61c3a2e89e7a6c3b2c3f52d3e5d1bbf86dbd59715ab3e12e572fb18f3c35";

    // Thời gian hết hạn của token (có thể cấu hình trong application.properties)
    // @Value("${jwt.expiration}")
    private long EXPIRATION = 86400; // 24 giờ = 86400 giây

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + EXPIRATION * 1000);  // Chuyển đổi sang milliseconds

        // Log thông tin về thời gian hết hạn của token
        System.out.println("Token issued at: " + now);
        System.out.println("Token will expire at: " + expirationTime);

        return Jwts.builder()
                .setSubject(username)
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
}
