package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.FavoritePlaylist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritePlaylistRepository extends JpaRepository<FavoritePlaylist, String> {
}
