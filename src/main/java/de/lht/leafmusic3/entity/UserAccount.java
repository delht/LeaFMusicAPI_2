package de.lht.leafmusic3.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "user_accounts")
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserAccount {

    @Id
    @Column(name = "id_user", updatable = false, nullable = false)
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

    @CreationTimestamp
    private Timestamp createdAt;

    @PrePersist
    public void generateId() {
        if (idUser == null || idUser.isEmpty()) {
            this.idUser = UUID.randomUUID().toString();
        }
        if (role == null) {
            this.role = Role.USER;
        }
    }


}
