package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, String> {

    List<Genre> findByNameContainingIgnoreCase(String name);

}
