package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.customlist.CustomListDTO;
import de.lht.leafmusic3.entity.CustomList;
import de.lht.leafmusic3.mapper.CustomListMapper;
import de.lht.leafmusic3.repository.CustomListRepository;
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


    public List<CustomListDTO> getAll() {
        List<CustomList> customLists = customListRepository.findAll();
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





}
