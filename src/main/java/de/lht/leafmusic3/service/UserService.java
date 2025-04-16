package de.lht.leafmusic3.service;


import de.lht.leafmusic3.config.JwtUtil;
import de.lht.leafmusic3.dto.login.LoginResponse;
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

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    //    ===================================================================================

    public UserAccount registerUser(String email, String password) {
        //Kiểm tra định dạng email
        if (!isValidEmail(email)) {
            throw new RuntimeException("Tài khoản đăng ký phải là một địa chỉ email hợp lệ");
        }

        //Kiểm tra trùng email
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        //Lấy phần trước @ làm username
        String username = email.split("@")[0];

        String hashedPassword = encodeMD5(password);

        UserAccount account = UserAccount.builder()
                .username(username)
                .password(hashedPassword)
                .email(email)
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

    public LoginResponse login(String username, String password) {
        String encodedPassword = encodeMD5(password);

        UserAccount user = userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(encodedPassword))
                .orElseThrow(() -> new RuntimeException("Lỗi xác thực"));

        String token = jwtUtil.generateToken(user.getUsername(),user.getIdUser(), user.getEmail());

//        return jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token, user.getUsername(), user.getIdUser(), user.getEmail());
    }

}
