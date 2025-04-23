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
    private final JwtUtil jwtUtil;

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

        if (!isValidEmail(email)) {
            throw new RuntimeException("Tài khoản đăng ký phải là một địa chỉ email hợp lệ");
        }

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

    public LoginResponse login(String email, String password) {
        String encodedPassword = encodeMD5(password);

        UserAccount user = userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(encodedPassword))
                .orElseThrow(() -> new RuntimeException("Lỗi xác thực"));

        String token = jwtUtil.generateToken(user.getUsername(),user.getIdUser(), user.getEmail());

//        return jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token, user.getUsername(), user.getIdUser(), user.getEmail(), String.valueOf(user.getRole()), user.getIdArtist(), user.getUpload());
    }


    public void changePassword(String id, String oldPassword, String newPassword) {
        UserAccount user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        String oldEncoded = encodeMD5(oldPassword);
        if (!user.getPassword().equals(oldEncoded)) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }

        String newEncoded = encodeMD5(newPassword);
        user.setPassword(newEncoded);

        userRepository.save(user);
    }





}
