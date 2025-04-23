package de.lht.leafmusic3.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String username;
    private String userId;
    private String email;
    private String role;
    private Long idArtist;
    private int upload;
}