package de.lht.leafmusic3.dto.user;

import de.lht.leafmusic3.entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private String idUser;
    private String username;
    private String email;
    private Role role;
}
