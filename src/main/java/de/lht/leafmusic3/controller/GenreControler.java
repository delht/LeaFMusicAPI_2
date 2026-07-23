package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.ApiResponse;
import de.lht.leafmusic3.dto.genre.GenreDTO;
import de.lht.leafmusic3.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreControler {
    private final GenreService genreService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<GenreDTO>>> getGenres() {
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Lấy danh sách thể loại thành công",
                        genres
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GenreDTO>> getGenreById(@PathVariable int id) {
        GenreDTO genre = genreService.getGenreById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Lấy thể loại thành công",
                        genre
                )
        );
    }

//    =============================================================================================

    @PostMapping("/auth/add")
    public ResponseEntity<ApiResponse<GenreDTO>> addGenre(@RequestBody GenreDTO genreDTO) {
        GenreDTO genre = genreService.addGenre(genreDTO);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Thêm thể loại thành công",
                        genre
                )
        );
    }

    @DeleteMapping("/auth/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGenre(@PathVariable int id) {
        genreService.deleteGenre(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Xóa thể loại thành công",
                        null
                )
        );
    }

    @PutMapping("/auth/update/{id}")
    public ResponseEntity<ApiResponse<GenreDTO>> updateGenre(@PathVariable int id, @RequestBody GenreDTO genreDTO) {
        GenreDTO updatedGenre = genreService.updateGenre(id, genreDTO);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Cập nhật thể loại thành công",
                        updatedGenre
                )
        );
    }

}
