package de.lht.leafmusic3.mapper;

import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
//    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(source = "idSong", target = "idSong")
    SongDTO toDTO(Song song);

    List<SongDTO> toDTOs(List<Song> songs);

}
