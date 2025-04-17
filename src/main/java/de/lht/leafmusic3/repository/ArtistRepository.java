package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, String> {

    @Query(value = "SELECT * FROM artists ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Artist> findRandomAritsts(@Param("limit") int limit);

    List<Artist> findByNameContainingIgnoreCase(String name);



}
