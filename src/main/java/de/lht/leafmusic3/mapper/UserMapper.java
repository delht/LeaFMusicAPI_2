package de.lht.leafmusic3.mapper;


import de.lht.leafmusic3.dto.user.UserDTO;
import de.lht.leafmusic3.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Ánh xạ từ UserAccount sang UserDTO
//    @Mapping(source = "idUser", target = "idUser")
//    @Mapping(source = "username", target = "username")
//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "role", target = "role")
    UserDTO toDTO(UserAccount user);

    // Ánh xạ danh sách UserAccount sang danh sách UserDTO
    List<UserDTO> toDTOs(List<UserAccount> users);
}

