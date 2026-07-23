package de.lht.leafmusic3.controller;


import de.lht.leafmusic3.dto.ApiResponse;
import de.lht.leafmusic3.dto.user.ChangePassword;
import de.lht.leafmusic3.dto.user.LoginResponse;
import de.lht.leafmusic3.dto.user.UserDTO;
import de.lht.leafmusic3.entity.UserAccount;
import de.lht.leafmusic3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {

        List<UserDTO> users = userService.getAllUsers();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Lấy danh sách thành công",
                        users
                )
        );
    }

//    =======================================================================

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAccount user) {
        UserAccount account = userService.registerUser(user.getEmail(), user.getPassWord());
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Đăng ký thành công",
                        account
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAccount user) {
        LoginResponse response = userService.login(user.getEmail(), user.getPassWord());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Đăng nhập thành công",
                        response
                )
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Object>> logout() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Đăng xuất thành công",
                        null
                )
        );
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<Object>> changePassword(
            @RequestBody ChangePassword changePassword) {

        userService.changePassword(changePassword);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Đổi mật khẩu thành công",
                        null
                )
        );
    }



}
