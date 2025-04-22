package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.CustomList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomListRepository extends JpaRepository<CustomList, String> {

    List<CustomList> findByIdUser(String idUser);


}
