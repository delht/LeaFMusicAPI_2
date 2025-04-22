package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.customsonglist.CustomSonglistDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.repository.CustomSonglistRepository;
import de.lht.leafmusic3.service.CustomSonglistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/custom-songlists")
public class CustomSonglistController {

    private final CustomSonglistService customSonglistService;
    private final CustomSonglistRepository customSonglistRepository;

//    @GetMapping("/{idList}")
//    public List<CustomSonglistDTO> getCustomSonglistsbyIdList(@PathVariable int idList) {
//        List<CustomSonglistDTO> customSonglist = customSonglistService.getByIdList(idList);
//        return customSonglist;
//    }

    @GetMapping("/{idList}")
    public List<SongDTO> getSongsByIdList(@PathVariable int idList) {
        return customSonglistService.getSongsByIdList(idList);
    }

//    ============================================================================

    @PostMapping("/add")
    public String addSongToList(@RequestParam int idList, @RequestParam int idSong) {
        return customSonglistService.addSongToList(idList, idSong);
    }

    @DeleteMapping("/remove")
    public String removeSongFromList(@RequestParam int idList, @RequestParam int idSong) {
        return customSonglistService.removeSongFromList(idList, idSong);
    }
//    ==================

    @GetMapping("/contains")
    public ResponseEntity<Map<String, Boolean>> checkSongExistsInList(
            @RequestParam int idList,
            @RequestParam int idSong) {

        boolean exists = customSonglistRepository.existsByIdListAndIdSong(idList, idSong);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);

        return ResponseEntity.ok(response);
    }

}