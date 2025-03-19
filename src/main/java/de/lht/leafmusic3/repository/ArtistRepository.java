package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, String> {
}
