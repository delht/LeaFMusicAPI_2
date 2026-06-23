package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

}
