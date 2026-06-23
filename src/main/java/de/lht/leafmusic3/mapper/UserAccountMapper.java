package de.lht.leafmusic3.mapper;

import de.lht.leafmusic3.dto.useraccount.UserAccountRequest;
import de.lht.leafmusic3.dto.useraccount.UserAccountRespone;
import de.lht.leafmusic3.entity.UserAccount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccount toEntity(UserAccountRequest request);

    UserAccountRespone toDto(UserAccount userAccount);
    List<UserAccountRespone> toDtoList(List<UserAccount> userAccounts);
}
