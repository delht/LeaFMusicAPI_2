package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.CustomList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomListRepository extends JpaRepository<CustomList, String> {

    List<CustomList> findByIdUser(String idUser);

    List<CustomList> findByState(int state);


//    @Procedure(procedureName = "clone_playlist")
//    void clonePlaylist(@Param("original_list_id") int originalListId, @Param("new_user_id") String newUserId);

}
