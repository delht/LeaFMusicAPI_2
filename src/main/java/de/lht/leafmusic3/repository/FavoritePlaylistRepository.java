package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.FavoritePlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoritePlaylistRepository extends JpaRepository<FavoritePlaylist, String> {
    List<FavoritePlaylist> findByIdUser(String idUser);

    Optional<FavoritePlaylist> findByIdUserAndIdSong(String idUser, int idSong);

    @Query("SELECT new de.lht.leafmusic3.dto.song.SongDTO(s.idSong, s.name, s.play, s.imageUrl, s.fileUrl, s.releaseDate, s.idArtist, s.idAlbum, s.idGenre) " +
            "FROM FavoritePlaylist f " +
            "JOIN Song s ON s.idSong = f.idSong " +
            "WHERE f.idUser = :idUser")
    List<SongDTO> getAllSongsByIdUser(@Param("idUser") String idUser);




}
