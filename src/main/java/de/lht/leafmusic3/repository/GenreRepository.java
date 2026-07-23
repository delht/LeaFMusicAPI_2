package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, String> {
    List<Genre> findByNameContainingIgnoreCase(String name);
    Optional<Genre> findByName(String name);
}
