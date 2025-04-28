package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.customlist.CustomListDTO;
import de.lht.leafmusic3.entity.CustomList;
import de.lht.leafmusic3.entity.CustomSonglist;
import de.lht.leafmusic3.mapper.CustomListMapper;
import de.lht.leafmusic3.repository.CustomListRepository;
import de.lht.leafmusic3.repository.CustomSonglistRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomListService {

    private final CustomListRepository customListRepository;
    private final CustomListMapper customListMapper;
    private final CustomSonglistRepository customSonglistRepository;


    public List<CustomListDTO> getAll() {
        List<CustomList> customLists = customListRepository.findAll();
        return customListMapper.toDtos(customLists);
    }

    public List<CustomListDTO> getByState(int state) {
        List<CustomList> customLists = customListRepository.findByState(state);
        return customListMapper.toDtos(customLists);
    }

    public List<CustomListDTO> getByIdUser(String idUser) {
        List<CustomList> customLists = customListRepository.findByIdUser(idUser);
        return customListMapper.toDtos(customLists);
    }

//    ======================================================================================

    public CustomList createCustomList(String name, String idUser) {
        CustomList customList = new CustomList();
        customList.setName(name);
        customList.setIdUser(idUser);
        customList.setState(0);
        return customListRepository.save(customList);
    }

    public void updateCustomList(int idList, String name) {
        CustomList customList = customListRepository.findById(String.valueOf(idList))
                .orElseThrow(()->new RuntimeException("ko tim thay ds"));
        customList.setName(name);
        customListRepository.save(customList);
    }

    public void deleteCustomList(int idList) {
        customListRepository.deleteById(String.valueOf(idList));
    }


///    ========================================================================================

    public void setPublicCustomList(int idList) {
        CustomList customList = customListRepository.findById(String.valueOf(idList))
                .orElseThrow(()->new RuntimeException("ko tim thay ds"));

        int state = customList.getState();
        if (state == 0) {
            customList.setState(1);
        } else {
            customList.setState(0);
        }
//        customList.setState(1);
        customListRepository.save(customList);
    }

//    public void clonePlaylist(int originalListId, String newUserId) {
//        customListRepository.clonePlaylist(originalListId, newUserId);
//    }

    @Transactional
    public void clonePlaylist(int originalListId, String newUserId) {

        ///Laay ds goc
        CustomList originalList = customListRepository.findById(String.valueOf(originalListId))
                .orElseThrow(() -> new RuntimeException("DS ko ton tai"));

        ///Tao ds moi
        CustomList newList = new CustomList();
        newList.setName(originalList.getName() + " (Copy)");
        newList.setIdUser(newUserId);
        newList.setState(0);

        CustomList savedList = customListRepository.save(newList);

        /// Copy nhac
        List<CustomSonglist> originalSongs = customSonglistRepository.findByIdList(originalListId);

        List<CustomSonglist> newSongs = new ArrayList<>();
        for (CustomSonglist song : originalSongs) {
            CustomSonglist newSong = new CustomSonglist();
            newSong.setIdList(savedList.getIdList());
            newSong.setIdSong(song.getIdSong());
            newSongs.add(newSong);
        }

        customSonglistRepository.saveAll(newSongs);
    }



}
