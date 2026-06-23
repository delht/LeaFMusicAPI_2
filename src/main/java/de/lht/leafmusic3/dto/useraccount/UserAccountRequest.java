package de.lht.leafmusic3.dto.useraccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAccountRequest {
    private String username;
    private String password;
    private String email;
}
