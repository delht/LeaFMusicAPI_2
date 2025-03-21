package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.FavoritePlaylist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritePlaylistRepository extends JpaRepository<FavoritePlaylist, String> {
    List<FavoritePlaylist> findByIdUser(String idUser);

    Optional<FavoritePlaylist> findByIdUserAndIdSong(String idUser, int idSong);
}
