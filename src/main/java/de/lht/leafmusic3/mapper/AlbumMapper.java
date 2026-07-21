package de.lht.leafmusic3.mapper;

import de.lht.leafmusic3.dto.album.AlbumRequest;
import de.lht.leafmusic3.dto.album.AlbumRespone;
import de.lht.leafmusic3.entity.Album;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    Album toEntity(AlbumRequest request);

    AlbumRespone toDto(Album album);
    List<AlbumRespone> toDtoList(List<Album> albums);

}
