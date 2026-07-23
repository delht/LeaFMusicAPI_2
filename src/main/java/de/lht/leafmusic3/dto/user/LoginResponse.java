package de.lht.leafmusic3.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String userName;
    private String userId;
    private String email;
    private String role;
    private Long idArtist;
    private int upload;
}