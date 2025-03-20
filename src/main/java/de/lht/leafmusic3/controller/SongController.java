package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    @GetMapping("/all")
    public List<SongDTO> getSongs() {
        List<SongDTO> songs = songService.getAllSongs();
        return songs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable int id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @GetMapping("/random")
    public List<SongDTO> getRandomSongs(@RequestParam(defaultValue = "5") int limit) {
        return songService.getRandomSongs(limit);
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<List<SongDTO>> getSongsByArtistId(@PathVariable int id) {
        return ResponseEntity.ok(songService.findSongsByArtist(id));
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<List<SongDTO>> getSongsByAlbumId(@PathVariable int id) {
        return ResponseEntity.ok(songService.findSongsByAlbum(id));
    }

}
