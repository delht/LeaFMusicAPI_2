package de.lht.leafmusic3.controller;


import de.lht.leafmusic3.config.JwtUtil;
import de.lht.leafmusic3.dto.ApiResponse;
import de.lht.leafmusic3.exception.AppException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    // POST /auth/refresh
    // Accepts JSON { "refreshToken": ".." } or Authorization: Bearer <token>
    @PostMapping("/auth/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody(required = false) Map<String, String> body,
                                          @RequestHeader(value = "Authorization", required = false) String authHeader) {

        String refreshToken = null;
        if (body != null && body.get("refreshToken") != null) {
            refreshToken = body.get("refreshToken");
        } else if (authHeader != null && authHeader.startsWith("Bearer ")) {
            refreshToken = authHeader.substring(7);
        }

        if (refreshToken == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Missing refresh token");
        }

        // Validate token signature and type
        if (!jwtUtil.validateToken(refreshToken) || !"refresh".equals(jwtUtil.getTokenType(refreshToken))) {
            throw new AppException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }

        Claims claims = jwtUtil.extractAllClaims(refreshToken);
        String username = claims.getSubject();
        String idUser = claims.get("idUser", String.class);
        String email = claims.get("email", String.class);

        String newAccess = jwtUtil.generateAccessToken(username, idUser, email);
        String newRefresh = jwtUtil.generateRefreshToken(username, idUser, email);

        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", newAccess);
        data.put("refreshToken", newRefresh);
        data.put("userName", username);
        data.put("userId", idUser);
        data.put("email", email);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Refresh token success", data));
    }

}

