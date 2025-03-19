package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    @GetMapping
    public List<SongDTO> getSongs() {
        List<SongDTO> songs = songService.getAllSongs();
        return songs;
    }
}
