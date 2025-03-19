package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.artist.ArtistDTO;
import de.lht.leafmusic3.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping
    public List<ArtistDTO> getArtists() {
        List<ArtistDTO> artists = artistService.getAllArtists();
        System.out.println(artists);
        return artists;
    }
}
