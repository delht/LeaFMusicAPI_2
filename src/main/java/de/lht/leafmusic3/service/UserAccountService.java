package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.useraccount.UserAccountRequest;
import de.lht.leafmusic3.dto.useraccount.UserAccountRespone;
import de.lht.leafmusic3.entity.Role;
import de.lht.leafmusic3.entity.UserAccount;
import de.lht.leafmusic3.mapper.UserAccountMapper;
import de.lht.leafmusic3.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import de.lht.leafmusic3.exceptions.AppException;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;


    public List<UserAccountRespone> getAllUsers() {
        return userAccountMapper.toDtoList(userAccountRepository.findAll());
    }

    public UserAccountRespone getUserById(String id) {
        UserAccount user = userAccountRepository.findById(id)
                .orElseThrow(()-> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng"));
        return userAccountMapper.toDto(user);
    }

    public UserAccountRespone createUser(UserAccountRequest request) {
        UserAccount user = userAccountMapper.toEntity(request);
        user.setRole(Role.USER);
        user.setUploadQuota(100);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        UserAccount saveUser = userAccountRepository.save(user);
        return userAccountMapper.toDto(saveUser);
    }

    public UserAccountRespone updateUser(String id, UserAccountRequest request) {
        UserAccount user = userAccountRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy người dùng"));

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        UserAccount updatedUser = userAccountRepository.save(user);

        return userAccountMapper.toDto(updatedUser);
    }

    public void DeleteUser(String id) {
        UserAccount user = userAccountRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy người dùng"));
        userAccountRepository.delete(user);
    }

}
