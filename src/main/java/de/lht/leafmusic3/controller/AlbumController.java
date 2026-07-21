package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.common.ApiResponse;
import de.lht.leafmusic3.dto.album.AlbumRequest;
import de.lht.leafmusic3.dto.album.AlbumRespone;
import de.lht.leafmusic3.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/all")
    public List<AlbumRespone> getAll() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/{id}")
    public AlbumRespone getById(@PathVariable String id) {
        return albumService.getAlbumById(id);
    }

    @PostMapping("/create")
    public AlbumRespone create(@RequestBody AlbumRequest request) {
        return albumService.createAlbum(request);
    }

    @PutMapping("/update/{id}")
    public AlbumRespone updateAlbum(
            @PathVariable String id,
            @RequestBody AlbumRequest request) {
        return albumService.updateAlbum(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable String id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.ok(
          new ApiResponse<>(true, "Xóa thành công", null)
        );
    }

}
