package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, String> {
}
