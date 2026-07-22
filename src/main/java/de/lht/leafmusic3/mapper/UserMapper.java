package de.lht.leafmusic3.mapper;


import de.lht.leafmusic3.dto.user.UserDTO;
import de.lht.leafmusic3.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserAccount user);
    List<UserDTO> toDTOs(List<UserAccount> users);
}

