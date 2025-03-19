package de.lht.leafmusic3.mapper;

import de.lht.leafmusic3.dto.artist.ArtistDTO;
import de.lht.leafmusic3.entity.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    ArtistDTO toDTO(Artist artist);

    List<ArtistDTO> toDTOs(List<Artist> artists);

}
