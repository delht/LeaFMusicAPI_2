package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.genre.GenreDTO;
import de.lht.leafmusic3.entity.Genre;
import de.lht.leafmusic3.mapper.GenreMapper;
import de.lht.leafmusic3.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<GenreDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        System.out.println(genres);
        return genreMapper.toDTOs(genres);
    }
}
