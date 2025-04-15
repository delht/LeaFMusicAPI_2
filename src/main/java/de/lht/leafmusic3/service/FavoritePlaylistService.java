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
        log.info("Fetched {} favorite playlists", favoritePlaylists.size());
        return favoritePlaylistMapper.toDTOs(favoritePlaylists);
    }

    public FavoritePlaylist addSongToFavorites(String idUser, int idSong) {
        // Kiểm tra xem bài hát đã có trong danh sách yêu thích của người dùng hay chưa
        Optional<FavoritePlaylist> existingFavorite = favoritePlaylistRepository.findByIdUserAndIdSong(idUser, idSong);

        if (existingFavorite.isPresent()) {
            // Nếu bài hát đã có trong danh sách yêu thích, trả về thông báo
            log.warn("Song {} is already in favorites for user {}", idSong, idUser);
            throw new IllegalArgumentException("Bài hát đã có trong danh sách yêu thích!");
        }

        // Nếu bài hát chưa có, thêm vào danh sách yêu thích
        FavoritePlaylist favorite = new FavoritePlaylist();
        favorite.setName("Temp");
        favorite.setIdUser(idUser);
        favorite.setIdSong(idSong);
        log.info("Adding song {} to favorites for user {}", idSong, idUser);

        return favoritePlaylistRepository.save(favorite);
    }


    public List<FavoritePlaylist> getFavoritesByUser(String idUser) {
        log.info("Fetching favorites for user {}", idUser);
        return favoritePlaylistRepository.findByIdUser(idUser);
    }

    public String removeFavoriteSong(String idUser, int idSong) {
        log.info("Attempting to remove favorite song: idUser={}, idSong={}", idUser, idSong);

        Optional<FavoritePlaylist> favorite = favoritePlaylistRepository.findByIdUserAndIdSong(idUser, idSong);
        if (favorite.isEmpty()) {
            log.warn("No favorite song found for user {} and song {}", idUser, idSong);
            return "Bài hát không có trong danh sách yêu thích!";
        }

        try {
            favoritePlaylistRepository.delete(favorite.get());
            log.info("Successfully removed song {} from user {}'s favorites", idSong, idUser);
            return "Đã xóa bài hát khỏi danh sách yêu thích!";
        } catch (Exception e) {
            log.error("Error occurred while deleting favorite song for user {}: {}", idUser, e.getMessage());
            return "Có lỗi xảy ra khi xóa bài hát!";
        }
    }
}

