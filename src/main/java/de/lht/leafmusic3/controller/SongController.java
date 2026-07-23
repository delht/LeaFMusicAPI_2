//package de.lht.leafmusic3.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import de.lht.leafmusic3.dto.album.AlbumRequestDTO;
//import de.lht.leafmusic3.dto.song.SongDTO;
//import de.lht.leafmusic3.dto.song.SongRequestDTO;
//import de.lht.leafmusic3.dto.song.SuggestionRequest;
//import de.lht.leafmusic3.entity.Album;
//import de.lht.leafmusic3.entity.Song;
//import de.lht.leafmusic3.service.SongService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/songs")
//public class SongController {
//    private final SongService songService;
//
//    @GetMapping("/all")
//    public List<SongDTO> getSongs() {
//        List<SongDTO> songs = songService.getAllSongs();
//        return songs;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<SongDTO> getSongById(@PathVariable int id) {
//        return ResponseEntity.ok(songService.getSongById(id));
//    }
//
//    @GetMapping("/random")
//    public List<SongDTO> getRandomSongs(@RequestParam(defaultValue = "5") int limit) {
//        return songService.getRandomSongs(limit);
//    }
//
//    @GetMapping("/artist/{id}")
//    public ResponseEntity<List<SongDTO>> getSongsByArtistId(@PathVariable int id) {
//        return ResponseEntity.ok(songService.findSongsByArtist(id));
//    }
//
//    @GetMapping("/album/{id}")
//    public ResponseEntity<List<SongDTO>> getSongsByAlbumId(@PathVariable int id) {
//        return ResponseEntity.ok(songService.findSongsByAlbum(id));
//    }
//
//    @GetMapping("/genre/{id}")
//    public ResponseEntity<List<SongDTO>> getSongsByGenreId(@PathVariable int id) {
//        return ResponseEntity.ok(songService.findSongsByGenre(id));
//    }
//
////    ===========================================================================================
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @PostMapping("/auth/add")
//    public ResponseEntity<?> addSong(
//            @RequestParam("img") MultipartFile img,
//            @RequestParam("audio") MultipartFile audio,
//            @RequestParam("song") String songRequestJson) {
//
//        try {
//            SongRequestDTO songRequest;
//            try {
//                songRequest = objectMapper.readValue(songRequestJson, SongRequestDTO.class);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body("Lỗi chuyển đổi songRequestDTO: " + e.getMessage());
//            }
//
//            Song song = songService.createSong(img, audio, songRequest);
//            return ResponseEntity.ok(song);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Lỗi khi thêm song: " + e.getMessage());
//        }
//    }
//
//    @DeleteMapping("/auth/delete/id/{id}")
//    public ResponseEntity<?> deleteSong(@PathVariable("id") int id) {
//        try {
//            songService.deleteSong(id);
//            return ResponseEntity.ok().build();
//        }catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
//
//    @PutMapping("/auth/update/id/{id}")
//    public ResponseEntity<?> updateArtist(
//            @PathVariable int id,
//            @RequestParam(value = "img", required = false) MultipartFile img,
//            @RequestParam(value = "audio", required = false) MultipartFile audio,
//            @RequestParam("song") String songRequestJson) {
//        try {
//            SongRequestDTO songRequest;
//            try {
//                songRequest = objectMapper.readValue(songRequestJson, SongRequestDTO.class);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body("Lỗi chuyển đổi dữ liệu song: " + e.getMessage());
//            }
//
//            Song updatedSong = songService.updateSong(id, img, audio, songRequest);
//            return ResponseEntity.ok(updatedSong);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Lỗi khi cập nhật song: " + e.getMessage());
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Không tìm thấy song: " + e.getMessage());
//        }
//    }
//
//
////    ===========================================================================================
//
//
//    @GetMapping("/upload/{id}")
//    public ResponseEntity<List<SongDTO>> getSongsByUploadBy(@PathVariable String id) {
//        return ResponseEntity.ok(songService.getSongbyUser(id));
//    }
//
//
////    ===========================================================================================
//
//    @PostMapping("/suggest")
//    public List<Song> suggestSongs(@RequestBody SuggestionRequest request) {
//        return songService.getSuggestedSongs(request.getArtistIds(), request.getGenreIds(), 5);
//    }
//
//
//
//}
