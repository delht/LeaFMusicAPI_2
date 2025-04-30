package de.lht.leafmusic3.mapper;

import de.lht.leafmusic3.dto.customsonglist.CustomSonglistDTO;
import de.lht.leafmusic3.entity.CustomSonglist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomSonglistMapper {
    CustomSonglistMapper INSTANCE = Mappers.getMapper(CustomSonglistMapper.class);

//    @Mapping(source = "idItem", target = "idItem")
    CustomSonglistDTO toDto(CustomSonglist customSonglist);

    List<CustomSonglistDTO> toDtos(List<CustomSonglist> customSonglists);
}
