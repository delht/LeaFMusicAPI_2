package de.lht.leafmusic3.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_accounts")
//@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
public class UserAccount {

    @Id
    @Column(name = "id_user")
    private String idUser;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password; // Lưu BCrypt hash

    @Column(name = "email", unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

}
