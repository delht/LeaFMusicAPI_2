package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.favoriteplaylist.FavoritePlaylistDTO;
import de.lht.leafmusic3.entity.FavoritePlaylist;
import de.lht.leafmusic3.mapper.FavoritePlaylistMapper;
import de.lht.leafmusic3.repository.FavoritePlaylistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoritePlaylistService {
    private final FavoritePlaylistRepository favoritePlaylistRepository;
    private final FavoritePlaylistMapper favoritePlaylistMapper;

    public List<FavoritePlaylistDTO> getAllFavoritePlaylists() {
        List<FavoritePlaylist> favoritePlaylists = favoritePlaylistRepository.findAll();
        System.out.println(favoritePlaylists);
        return favoritePlaylistMapper.toDTOs(favoritePlaylists);
    }


    public FavoritePlaylist addSongToFavorites(String idUser, int idSong) {
        FavoritePlaylist favorite = new FavoritePlaylist();
        favorite.setName("Temp");
        favorite.setIdUser(idUser);
        favorite.setIdSong(idSong);
        return favoritePlaylistRepository.save(favorite);
    }

    public List<FavoritePlaylist> getFavoritesByUser(String idUser) {
        return favoritePlaylistRepository.findByIdUser(idUser);
    }

    public String removeFavoriteSong(String idUser, int idSong) {
        log.info("Xóa bài hát: idUser={}, idSong={}", idUser, idSong);

        Optional<FavoritePlaylist> favorite = favoritePlaylistRepository.findByIdUserAndIdSong(idUser, idSong);
        if (favorite.isEmpty()) {
            log.warn("Không tìm thấy bài hát yêu thích của user {}", idUser);
            return "Bài hát không có trong danh sách yêu thích!";
        }

        favoritePlaylistRepository.delete(favorite.get());
        log.info("Đã xóa bài hát {} khỏi danh sách yêu thích của user {}", idSong, idUser);
        return "Đã xóa bài hát khỏi danh sách yêu thích!";
    }

}
