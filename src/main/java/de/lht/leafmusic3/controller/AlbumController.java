package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
