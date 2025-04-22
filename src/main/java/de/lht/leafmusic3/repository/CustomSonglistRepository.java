package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.dto.customsonglist.CustomSonglistDTO;
import de.lht.leafmusic3.entity.CustomSonglist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomSonglistRepository extends JpaRepository<CustomSonglist, Integer> {

    List<CustomSonglist> findByIdList(Integer id);

    void deleteByIdListAndIdSong(int idList, int idSong);
    boolean existsByIdListAndIdSong(int idList, int idSong);

}
