package de.lht.leafmusic3.repository;


import de.lht.leafmusic3.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, String> {
    Optional<UserAccount> findByUserName(String username);
    Optional<UserAccount> findByEmail(String email);
}
