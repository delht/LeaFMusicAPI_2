package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.favoriteplaylist.FavoritePlaylistDTO;
import de.lht.leafmusic3.service.FavoritePlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/favoritelists")
public class FavoritePlaylistController {
    private final FavoritePlaylistService favoritePlaylistService;

    @GetMapping
    public List<FavoritePlaylistDTO> getFavoritePlaylists() {
        List<FavoritePlaylistDTO> favoritePlaylists = favoritePlaylistService.getAllFavoritePlaylists();
        return favoritePlaylists;
    }
}
