//package de.lht.leafmusic3.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import de.lht.leafmusic3.dto.album.Album2DTO;
//import de.lht.leafmusic3.dto.album.AlbumRequestDTO;
//import de.lht.leafmusic3.dto.artist.Artist2DTO;
//import de.lht.leafmusic3.dto.artist.ArtistDTO;
//import de.lht.leafmusic3.dto.artist.ArtistRequestDTO;
//import de.lht.leafmusic3.dto.song.SongDTO;
//import de.lht.leafmusic3.entity.Album;
//import de.lht.leafmusic3.entity.Artist;
//import de.lht.leafmusic3.entity.Song;
//import de.lht.leafmusic3.service.ArtistService;
//import de.lht.leafmusic3.service.SongService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/artists")
//public class ArtistController {
//    private final ArtistService artistService;
//    private final SongService songService;
//
//    @GetMapping("/all")
//    public List<ArtistDTO> getArtists() {
//        List<ArtistDTO> artists = artistService.getAllArtists();
//        System.out.println(artists);
//        return artists;
//    }
//
//    @GetMapping("/v2/all")
//    public List<Artist2DTO> getAllArtistsV2() {
//        return artistService.getAllArtist2();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable String id) {
//        ArtistDTO artist = artistService.getArtistById(id);
//        return ResponseEntity.ok(artist);
//    }
//
//    @GetMapping("/random")
//    public List<ArtistDTO> getRandomArtists(@RequestParam(defaultValue = "5") int limit) {
//        return artistService.getRandomArtists(limit);
//    }
//
////    ===================================================================
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @PostMapping("/auth/add")
//    public ResponseEntity<?> addArtist(
//            @RequestParam("img") MultipartFile img,
//            @RequestParam("artist") String artistRequestJson) {
//
//        try {
//            ArtistRequestDTO artistRequest;
//            try {
//                artistRequest = objectMapper.readValue(artistRequestJson, ArtistRequestDTO.class);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi Artist_RequestDTO: " + e.getMessage());
//            }
//
//            Artist artist = artistService.createArtist(img, artistRequest);
//            return ResponseEntity.ok(artist);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thêm album: " + e.getMessage());
//        }
//    }
//
//    @DeleteMapping("/auth/delete/id/{id}")
//    public ResponseEntity<?> deleteArtist(@PathVariable("id") String id) {
//        try {
//            artistService.deleteArtist(id);
//            return ResponseEntity.ok().build();
//        }catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
//
//    @PutMapping("/auth/update/id/{id}")
//    public ResponseEntity<?> updateArtist(
//            @PathVariable String id,
//            @RequestParam(value = "img", required = false) MultipartFile img,
//            @RequestParam("artist") String artistRequestJson
//    ) {
//        try {
//            ArtistRequestDTO albumRequest;
//            try {
//                albumRequest = objectMapper.readValue(artistRequestJson, ArtistRequestDTO.class);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body("Lỗi chuyển đổi dữ liệu artist: " + e.getMessage());
//            }
//
//            Artist updatedArtist = artistService.updateArtist(id, img, albumRequest);
//            return ResponseEntity.ok(updatedArtist);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Lỗi khi cập nhật artist: " + e.getMessage());
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Không tìm thấy artist: " + e.getMessage());
//        }
//    }
//
//}
