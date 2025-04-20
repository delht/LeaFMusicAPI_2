package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.dto.album.Album2DTO;
import de.lht.leafmusic3.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByIdArtist(int artistId);

    @Query(value = "SELECT * FROM albums ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Album> findRandomAlbums(@Param("limit") int limit);

    List<Album> findByNameContainingIgnoreCase(String name);


    @Query(value = "SELECT a.id_album, a.name FROM albums a", nativeQuery = true)
    List<Album2DTO> findAllAlbum();
}
