package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.artist.ArtistDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.Song;
import de.lht.leafmusic3.service.ArtistService;
import de.lht.leafmusic3.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    @GetMapping("/all")
    public List<ArtistDTO> getArtists() {
        List<ArtistDTO> artists = artistService.getAllArtists();
        System.out.println(artists);
        return artists;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable String id) {
        ArtistDTO artist = artistService.getArtistById(id);
        return ResponseEntity.ok(artist);
    }

    @GetMapping("/random")
    public List<ArtistDTO> getRandomArtists(@RequestParam(defaultValue = "5") int limit) {
        return artistService.getRandomArtists(limit);
    }



}
