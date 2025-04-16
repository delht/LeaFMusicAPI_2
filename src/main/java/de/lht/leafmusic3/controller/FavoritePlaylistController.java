package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.favoriteplaylist.FavoritePlaylistDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.FavoritePlaylist;
import de.lht.leafmusic3.service.FavoritePlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(
            @RequestParam String idUser,
            @RequestParam int idSong) {
        try {
            FavoritePlaylist favorite = favoritePlaylistService.addSongToFavorites(idUser, idSong);
            return ResponseEntity.ok("Bài hát đã được thêm vào danh sách yêu thích!");
        } catch (IllegalArgumentException e) {
            // Nếu bài hát đã có, trả về thông báo lỗi
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFavoriteSong(@RequestParam String idUser, @RequestParam int idSong) {
        String result = favoritePlaylistService.removeFavoriteSong(idUser, idSong);
        if (result.contains("không có")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/list/{idUser}")
    public ResponseEntity<List<FavoritePlaylist>> getFavorites(@PathVariable String idUser) {
        List<FavoritePlaylist> favorites = favoritePlaylistService.getFavoritesByUser(idUser);
        return ResponseEntity.ok(favorites);
    }

    @GetMapping("/songs/{idUser}")
    public ResponseEntity<List<SongDTO>> getSongs(@PathVariable String idUser) {
        List<SongDTO> songs = favoritePlaylistService.getFavoriteSongs(idUser);
        return ResponseEntity.ok(songs);
    }

}
