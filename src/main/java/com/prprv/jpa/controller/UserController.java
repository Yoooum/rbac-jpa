package com.prprv.jpa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prprv.jpa.entity.User;
import com.prprv.jpa.entity.request.UserRequest;
import com.prprv.jpa.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    final UserServiceImpl userService;
    final ObjectMapper objectMapper;
    @PostMapping("/create")
    public void createUser(@RequestBody UserRequest req) {
        try {
            System.out.println(objectMapper.writeValueAsString(req));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable String username) {
        System.out.println(username);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody UserRequest req) {
        try {
            System.out.println(objectMapper.writeValueAsString(req));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/query/{username}")
    public void findUser(@PathVariable String username) {
        System.out.println(username);
    }

    @GetMapping("/query/all")
    public List<User> listUser() {
        return userService.findAll();
    }
    @GetMapping(value = "/query", params = {"page", "size"})
    public void listUser(@RequestParam int page, @RequestParam int size) {
        System.out.println(page + " " + size);
    }
}
