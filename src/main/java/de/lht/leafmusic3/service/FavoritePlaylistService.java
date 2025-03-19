package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.favoriteplaylist.FavoritePlaylistDTO;
import de.lht.leafmusic3.entity.FavoritePlaylist;
import de.lht.leafmusic3.mapper.FavoritePlaylistMapper;
import de.lht.leafmusic3.repository.FavoritePlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritePlaylistService {
    private final FavoritePlaylistRepository favoritePlaylistRepository;
    private final FavoritePlaylistMapper favoritePlaylistMapper;

    public List<FavoritePlaylistDTO> getAllFavoritePlaylists() {
        List<FavoritePlaylist> favoritePlaylists = favoritePlaylistRepository.findAll();
        System.out.println(favoritePlaylists);
        return favoritePlaylistMapper.toDTOs(favoritePlaylists);
    }

}
