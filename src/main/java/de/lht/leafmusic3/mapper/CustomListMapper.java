package de.lht.leafmusic3.mapper;

import de.lht.leafmusic3.dto.customlist.CustomListDTO;
import de.lht.leafmusic3.entity.CustomList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomListMapper {
    CustomListMapper INSTANCE = Mappers.getMapper(CustomListMapper.class);

    @Mapping(source = "idList", target = "idList")
    @Mapping(source = "state", target = "state")
    CustomListDTO toDto(CustomList customList);

    List<CustomListDTO> toDtos(List<CustomList> customList);
}
