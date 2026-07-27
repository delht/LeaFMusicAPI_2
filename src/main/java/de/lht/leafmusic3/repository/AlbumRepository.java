package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.dto.album.Album2DTO;
import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Optional<List<Album>> findByIdArtist(int artistId);

    @Query(value = "SELECT * FROM albums ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Album> findRandomAlbums(@Param("limit") int limit);

    List<Album> findByNameContainingIgnoreCase(String name);


    @Query(value = "SELECT a.id_album, a.name, a.id_artist FROM albums a", nativeQuery = true)
    Page<Album2DTO> findAllAlbum(Pageable pageable);

    List<Album> findByUploadBy (String idUser);

}
