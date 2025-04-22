package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.customlist.CustomListDTO;
import de.lht.leafmusic3.entity.CustomList;
import de.lht.leafmusic3.service.CustomListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customlists")
public class CustomListController {
    private final CustomListService customListService;

    @GetMapping
    public List<CustomListDTO> getCustomLists() {
        List<CustomListDTO> customLists = customListService.getAll();
        return customLists;
    }


    @GetMapping("/user/{id}")
    public List<CustomListDTO> getCustomListById(@PathVariable String id) {
        List<CustomListDTO> customLists = customListService.getByIdUser(id);
        return customLists;
    }

//========================================================================================

    // 1. Tạo mới CustomList
    @PostMapping("/add")
    public ResponseEntity<CustomList> createCustomList(
            @RequestParam String name,
            @RequestParam String idUser) {
        CustomList newList = customListService.createCustomList(name, idUser);
        return ResponseEntity.ok(newList);
    }

    // 2. Cập nhật CustomList
    @PutMapping("/update/{idList}")
    public ResponseEntity<Void> updateCustomList(
            @PathVariable int idList,
            @RequestParam String name) {
        customListService.updateCustomList(idList, name);
        return ResponseEntity.ok().build();
    }

    // 3. Xóa CustomList
    @DeleteMapping("/delete/{idList}")
    public ResponseEntity<Void> deleteCustomList(
            @PathVariable int idList) {
        customListService.deleteCustomList(idList);
        return ResponseEntity.noContent().build();
    }




}
