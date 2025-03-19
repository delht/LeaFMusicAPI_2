package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

//    public AlbumController(AlbumService albumService) {
//        this.albumService = albumService;
//    }

    @GetMapping
    public List<AlbumDTO> getAlbums() {
        List<AlbumDTO> albums = albumService.getAllAlbums();
        System.out.println("Data: " + albums);
        return albums;
    }

}
