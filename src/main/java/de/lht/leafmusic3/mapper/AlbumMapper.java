package de.lht.leafmusic3.mapper;

import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class); //chưa dùng

    @Mapping(source = "idAlbum", target = "idAlbum")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "imageUrl", target = "imageUrl")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "idArtist", target = "idArtist")
    @Mapping(source = "uploadBy", target = "uploadBy")
    AlbumDTO toDTO(Album album);

    List<AlbumDTO> toDTOs(List<Album> albums);

}
