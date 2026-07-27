package de.lht.leafmusic3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.lht.leafmusic3.dto.ApiResponse;
import de.lht.leafmusic3.dto.album.AlbumRequestDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.dto.song.SongRequestDTO;
import de.lht.leafmusic3.dto.song.SuggestionRequest;
import de.lht.leafmusic3.entity.Album;
import de.lht.leafmusic3.entity.Song;
import de.lht.leafmusic3.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

//    @GetMapping("/all")
//    public List<SongDTO> getSongs() {
//        List<SongDTO> songs = songService.getAllSongs();
//        return songs;
//    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<SongDTO>>> getAllSongs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Danh sách tất cả bài hát",
                        songService.getAllSongs(page, size)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SongDTO>> getSongById(@PathVariable int id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Lấy thông tin bài hát thành công",
                        songService.getSongById(id)
                )
        );
    }

    //TODO Sửa random
    @GetMapping("/random")
    public List<SongDTO> getRandomSongs(@RequestParam(defaultValue = "5") int limit) {
        return songService.getRandomSongs(limit);
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<ApiResponse<List<SongDTO>>> getSongsByArtistId(@PathVariable int id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Lấy danh sách bài hát theo ca sĩ thành công",
                        songService.findSongsByArtist(id)
                )
        );
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<ApiResponse<List<SongDTO>>> getSongsByAlbumId(@PathVariable int id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Lấy danh sách bài hát theo album thành công",
                        songService.findSongsByAlbum(id)
                )
        );
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<ApiResponse<List<SongDTO>>> getSongsByGenreId(@PathVariable int id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Lấy danh sách bài hát theo thể loại thành công",
                        songService.findSongsByGenre(id)
                )
        );
    }

//    ===========================================================================================

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/auth/add")
    public ResponseEntity<?> addSong(
            @RequestParam("img") MultipartFile img,
            @RequestParam("audio") MultipartFile audio,
            @RequestParam("song") String songRequestJson) throws IOException {

        SongRequestDTO songRequest
                = objectMapper.readValue(songRequestJson, SongRequestDTO.class);

        Song song = songService.createSong(img, audio, songRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED,
                        "Thêm artist thành công.",
                        song
                ));
    }

    @DeleteMapping("/auth/delete/id/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable("id") int id) throws IOException {
        songService.deleteSong(id);
        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK,
                "Xóa song thành công.",
                null
        ));
    }

    @PutMapping("/auth/update/id/{id}")
    public ResponseEntity<?> updateArtist(
            @PathVariable int id,
            @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam(value = "audio", required = false) MultipartFile audio,
            @RequestParam("song") String songRequestJson) throws IOException {

        SongRequestDTO songRequest
                = objectMapper.readValue(songRequestJson, SongRequestDTO.class);

        Song song = songService.updateSong(id, img, audio, songRequest);;

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED,
                        "Sửa artist thành công.",
                        song
                ));

    }


//    ===========================================================================================


    @GetMapping("/upload/{id}")
    public ResponseEntity<List<SongDTO>> getSongsByUploadBy(@PathVariable String id) {
        return ResponseEntity.ok(songService.getSongbyUser(id));
    }


//    ===========================================================================================

    @PostMapping("/suggest")
    public List<Song> suggestSongs(@RequestBody SuggestionRequest request) {
        return songService.getSuggestedSongs(request.getArtistIds(), request.getGenreIds(), 5);
    }



}
