package de.lht.leafmusic3.controller;


import de.lht.leafmusic3.dto.user.UserDTO;
import de.lht.leafmusic3.service.UserService;
import lombok.RequiredArgsConstructor;
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
}
