package de.lht.leafmusic3.controller;


import de.lht.leafmusic3.dto.user.UserDTO;
import de.lht.leafmusic3.entity.UserAccount;
import de.lht.leafmusic3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping
    public List<UserDTO> getUsers() {
        List<UserDTO> users = userService.getAllUsers();
        System.out.println("Users to be returned: " + users);
        return users;
    }


//    =======================================================================

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAccount user) {
        try {
            return ResponseEntity.ok(userService.registerUser(user.getEmail(), user.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAccount user) {
        try {
            return ResponseEntity.ok(userService.login(user.getEmail(), user.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }

}
