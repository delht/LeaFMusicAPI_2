package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.genre.GenreDTO;
import de.lht.leafmusic3.entity.Genre;
import de.lht.leafmusic3.exception.AppException;
import de.lht.leafmusic3.mapper.GenreMapper;
import de.lht.leafmusic3.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<GenreDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genreMapper.toDTOs(genres);
    }

    public GenreDTO getGenreById(int id) {
        String genreId = String.valueOf(id);
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy thể loại"));;
        return genreMapper.toDTO(genre);
    }

    // ============================================================================================

    public GenreDTO addGenre(GenreDTO genreDTO) {

        if(genreRepository.findByName(genreDTO.getName()).isPresent()) {
            throw new AppException(HttpStatus.CONFLICT, "Thể loại đã tồn tại");
        }

        Genre genre = new Genre();
        genre.setName(genreDTO.getName());
        Genre saved = genreRepository.save(genre);
        return genreMapper.toDTO(saved);
    }

    public void deleteGenre(int id) {
        Genre genre = genreRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy thể loại"));
        genreRepository.deleteById(String.valueOf(id));
    }

    public GenreDTO updateGenre(int id, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy thể loại"));
        if(genreRepository.findByName(genreDTO.getName()).isPresent()) {
            throw new AppException(HttpStatus.CONFLICT, "Thể loại đã tồn tại");
        }
        genre.setName(genreDTO.getName());
        Genre updated = genreRepository.save(genre);
        return genreMapper.toDTO(updated);
    }




}
