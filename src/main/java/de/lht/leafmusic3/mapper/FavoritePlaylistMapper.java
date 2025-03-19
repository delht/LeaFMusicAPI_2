package de.lht.leafmusic3.mapper;

import de.lht.leafmusic3.dto.favoriteplaylist.FavoritePlaylistDTO;
import de.lht.leafmusic3.entity.FavoritePlaylist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoritePlaylistMapper {
    FavoritePlaylistMapper INSTANCE = Mappers.getMapper(FavoritePlaylistMapper.class);

    FavoritePlaylistDTO toDTO(FavoritePlaylist favoritePlaylist);

    List<FavoritePlaylistDTO> toDTOs(List<FavoritePlaylist> favoritePlaylists);
}
