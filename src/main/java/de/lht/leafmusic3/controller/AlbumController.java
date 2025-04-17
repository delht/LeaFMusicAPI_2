package de.lht.leafmusic3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.dto.album.AlbumRequestDTO;
import de.lht.leafmusic3.entity.Album;
import de.lht.leafmusic3.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

//    public AlbumController(AlbumService albumService) {
//        this.albumService = albumService;
//    }

    @GetMapping("/all")
    public List<AlbumDTO> getAlbums() {
        List<AlbumDTO> albums = albumService.getAllAlbums();
        System.out.println("Data: " + albums);
        return albums;
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<List<AlbumDTO>> getAlbumByArtistId(@PathVariable int id) {
        return ResponseEntity.ok(albumService.getAlbumsByArtist(id));
    }

    @GetMapping("/random")
    public List<AlbumDTO> getAlbumByRamdom(@RequestParam(defaultValue = "5") int limit) {
        return albumService.getRandomAlbums(limit);
    }

//    ===========================================================================================

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/add")
    public ResponseEntity<?> addAlbum(
            @RequestParam("img") MultipartFile img,
            @RequestParam("album") String albumRequestJson) {

        try {
            AlbumRequestDTO albumRequest;
            try {
                albumRequest = objectMapper.readValue(albumRequestJson, AlbumRequestDTO.class);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi Album_RequestDTO: " + e.getMessage());
            }

            Album album = albumService.addAlbum(img, albumRequest);
            return ResponseEntity.ok(album);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thêm album: " + e.getMessage());
        }
    }


    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable("id") int id) {
        try {
            albumService.deleteAlbum(id);
            return ResponseEntity.ok().build();
        }catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<?> updateAlbum(
            @PathVariable int id,
            @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam("album") String albumRequestJson
    ) {
        try {
            AlbumRequestDTO albumRequest;
            try {
                albumRequest = objectMapper.readValue(albumRequestJson, AlbumRequestDTO.class);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Lỗi chuyển đổi dữ liệu album: " + e.getMessage());
            }

            Album updatedAlbum = albumService.updateAlbum(id, img, albumRequest);
            return ResponseEntity.ok(updatedAlbum);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi cập nhật album: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy album: " + e.getMessage());
        }
    }
    



}
