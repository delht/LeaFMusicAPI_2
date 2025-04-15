package de.lht.leafmusic3.service;


import de.lht.leafmusic3.config.JwtUtil;
import de.lht.leafmusic3.dto.user.UserDTO;
import de.lht.leafmusic3.entity.Role;
import de.lht.leafmusic3.entity.UserAccount;
import de.lht.leafmusic3.mapper.UserMapper;
import de.lht.leafmusic3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
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

//    ===================================================================================
    public String encodeMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encoding password", e);
        }
    }

    public UserAccount registerUser(String username, String password) {
        if(userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        String hashedPassword = encodeMD5(password);

        UserAccount account = UserAccount.builder()
                .username(username)
                .password(hashedPassword)
                .role(Role.USER)
                .build();
        return userRepository.save(account);
    }

//    public UserAccount login(String username, String password) {
//        String encodedPassword = encodeMD5(password);
//        return userRepository.findByUsername(username)
//                .filter(user -> user.getPassword().equals(encodedPassword))
//                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
//    }

    private final JwtUtil jwtUtil;

    public String login(String username, String password) {
        String encodedPassword = encodeMD5(password);
        UserAccount user = userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(encodedPassword))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        return jwtUtil.generateToken(user.getUsername());
    }

}
