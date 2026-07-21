package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.common.ApiResponse;
import de.lht.leafmusic3.dto.useraccount.UserAccountRequest;
import de.lht.leafmusic3.dto.useraccount.UserAccountRespone;
import de.lht.leafmusic3.entity.UserAccount;
import de.lht.leafmusic3.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

//    @GetMapping("/v1")
//    public List<UserAccount> getAll() {
//        return userAccountService.getAllUsers();
//    }

    @GetMapping("/all")
    public List<UserAccountRespone> getAll() {
        return userAccountService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserAccountRespone getById(@PathVariable String id) {
        return userAccountService.getUserById(id);
    }

    @PostMapping("/create")
    public UserAccountRespone create(@RequestBody UserAccountRequest request) {
        return userAccountService.createUser(request);
    }

    @PutMapping("/update/{id}")
    public UserAccountRespone updateUser(
            @PathVariable String id,
            @RequestBody UserAccountRequest request) {
        return userAccountService.updateUser(id, request);
    }

//    @DeleteMapping("/delete/{id}")
//    public String deleteUser(@PathVariable String id) {
//        userAccountService.DeleteUser(id);
//        return "Xóa thành công";
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {

        userAccountService.DeleteUser(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa thành công", null)
        );
    }

}
