package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.genre.GenreDTO;
import de.lht.leafmusic3.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreControler {
    private final GenreService genreService;

    @GetMapping
    public List<GenreDTO> getGenres() {
        List<GenreDTO> genres = genreService.getAllGenres();
        return genres;
    }
}
