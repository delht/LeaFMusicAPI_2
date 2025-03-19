package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, String> {
}
