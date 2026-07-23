package de.lht.leafmusic3.mapper;

import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumDTO toDTO(Album album);
    List<AlbumDTO> toDTOs(List<Album> albums);
}
