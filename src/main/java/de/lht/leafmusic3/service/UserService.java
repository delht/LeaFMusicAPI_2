package de.lht.leafmusic3.service;


import de.lht.leafmusic3.dto.user.UserDTO;
import de.lht.leafmusic3.entity.UserAccount;
import de.lht.leafmusic3.mapper.UserMapper;
import de.lht.leafmusic3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    public UserService(UserRepository userRepository, UserMapper userMapper) {
//        this.userRepository = userRepository;
//        this.userMapper = userMapper;
//    } //KHÔNG DÙNG ĐOẠN NÀY VÌ ĐÃ CÓ: @RequiredArgsConstructor

    public List<UserDTO> getAllUsers() {
        List<UserAccount> users = userRepository.findAll();
        System.out.println("Users fetched from DB: " + users);
//        return userMapper.toDTOs(userRepository.findAll());
        return userMapper.toDTOs(users);
    }
}
