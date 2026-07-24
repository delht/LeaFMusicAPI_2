package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.Artist;
import de.lht.leafmusic3.entity.Song;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query(value = "SELECT * FROM songs ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    Optional<List<Song>> findRandomSongs(@Param("limit") int limit);

    Optional<List<Song>> findByIdArtist(int artistId);
    Optional<List<Song>> findByIdAlbum(int albumId);

    Optional<Song> findById(int id);;

    List<Song> findByNameContainingIgnoreCase(String name);

    Optional<List<Song>> findByIdGenre(int genreId);

    List<Song> findByUploadBy(String idUser);

    List<Song> findByIdArtistInOrIdGenreIn(List<Integer> artistIds, List<Integer> genreIds);

}
