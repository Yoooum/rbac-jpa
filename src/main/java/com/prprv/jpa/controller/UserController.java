package com.prprv.jpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prprv.jpa.entity.User;
import com.prprv.jpa.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yoooum
 */
@CrossOrigin
@RestController
@Tag(name = "用户管理")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    final UserServiceImpl userService;

    @PostMapping("/create")
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @PutMapping("/update")
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/select/{id}")
    public User selectUser(@PathVariable Long id) {
        return userService.selectUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/select")
    public Object selectUserPage(@RequestParam Integer page, @RequestParam Integer size) {
        return userService.selectUserPage(page, size);
    }
}
