package de.lht.leafmusic3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.lht.leafmusic3.dto.ApiResponse;
import de.lht.leafmusic3.dto.album.Album2DTO;
import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.dto.album.AlbumRequestDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
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

    @GetMapping("/all")
    public List<AlbumDTO> getAlbums() {
        List<AlbumDTO> albums = albumService.getAllAlbums();
        System.out.println("Data: " + albums);
        return albums;
    }

    @GetMapping("/v2/all")
    public List<Album2DTO> getAllAlbumsV2() {
        return albumService.getAllAlbums2();
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<ApiResponse<List<AlbumDTO>>> getAlbumByArtistId(@PathVariable int id) {
        return ResponseEntity.ok(
          new ApiResponse<>(
                  HttpStatus.OK,
                  "Lấy danh sách Album theo ca sĩ thành công",
                  albumService.getAlbumsByArtist(id)
          )
        );
    }

    //TODO Sửa random
    @GetMapping("/random")
    public List<AlbumDTO> getAlbumByRamdom(@RequestParam(defaultValue = "5") int limit) {
        return albumService.getRandomAlbums(limit);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AlbumDTO>> getAlbumById(@PathVariable int id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Lấy danh sách Album theo id thành công",
                        albumService.getAlbumById(id)
                )
        );
    }



//    ===========================================================================================

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/auth/add")
    public ResponseEntity<ApiResponse<Album>> addAlbum(
            @RequestParam("img") MultipartFile img,
            @RequestParam("album") String albumRequestJson) throws IOException {

        AlbumRequestDTO albumRequest =
                objectMapper.readValue(albumRequestJson, AlbumRequestDTO.class);

        Album album = albumService.addAlbum(img, albumRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED,
                        "Thêm album thành công.",
                        album
                ));
    }


    @DeleteMapping("/auth/delete/id/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAlbum(@PathVariable int id) throws IOException {

        albumService.deleteAlbum(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Xóa album thành công.",
                        null
                )
        );
    }

    @PutMapping("/auth/update/id/{id}")
    public ResponseEntity<ApiResponse<Album>> updateAlbum(
            @PathVariable int id,
            @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam("album") String albumRequestJson) throws IOException {

        AlbumRequestDTO albumRequest =
                objectMapper.readValue(albumRequestJson, AlbumRequestDTO.class);

        Album album = albumService.updateAlbum(id, img, albumRequest);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Cập nhật album thành công.",
                        album
                )
        );
    }

//    ===========================================================================================


    @GetMapping("/upload/{id}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByUploadBy(@PathVariable String id) {
        return ResponseEntity.ok(albumService.getAlbumbyUser(id));
    }


}
