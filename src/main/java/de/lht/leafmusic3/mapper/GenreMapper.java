package de.lht.leafmusic3.mapper;

import de.lht.leafmusic3.dto.genre.GenreDTO;
import de.lht.leafmusic3.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDTO toDTO(Genre genre);

    List<GenreDTO> toDTOs(List<Genre> genres);
}
