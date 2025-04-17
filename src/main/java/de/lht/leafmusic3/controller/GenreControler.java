package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.genre.GenreDTO;
import de.lht.leafmusic3.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


//    =============================================================================================

    @PostMapping("/add")
    public GenreDTO addGenre(@RequestBody GenreDTO genreDTO) {
        return genreService.addGenre(genreDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGenre(@PathVariable int id) {
        genreService.deleteGenre(id);
    }

    @PutMapping("/update/{id}")
    public GenreDTO updateGenre(@PathVariable int id, @RequestBody GenreDTO genreDTO) {
        return genreService.updateGenre(id, genreDTO);
    }

}
